package com.ZZZZ.OrderService.service.impl;

import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.request.OrderUpdateRequest;
import com.ZZZZ.OrderService.DTO.response.OrderDetailResponse;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.base.exception.ApplicationException;
import com.ZZZZ.OrderService.base.exception.ErrorCode;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.OrderService.kafka.OrderEventProducer;
import com.ZZZZ.OrderService.mapper.OrderMapper;
import com.ZZZZ.OrderService.repository.OrderRepo;
import com.ZZZZ.OrderService.service.OrderService;
import com.ZZZZ.commonDTO.Order.OrderCanceledEvent;
import com.ZZZZ.commonDTO.Order.OrderCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {

    OrderMapper orderMapper;
    OrderRepo orderRepo;
    OrderEventProducer producer;
    RedisTemplate<String, Object> redisTemplate;
    KafkaTemplate<String, OrderCanceledEvent> orderCanceledEventKafkaTemplate;

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN') and #id.equals(authentication.name)")
    public OrderDetailResponse createOrder(OrderCreationRequest request) {
        Order order = orderMapper.toOrder(request);
        orderRepo.save(order);

        OrderCreatedEvent event = orderMapper.toOrderCreatedEvent(order, "sending message created order");
        producer.sendOrderCreatedEvent(event);

        return orderMapper.toOrderDetailResponse(order);
    }


    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN') and #id.equals(authentication.name)")
    public OrderDetailResponse updateInformationOrder(String id, OrderUpdateRequest request) {
        Order order = orderRepo.findByIdAndDeletedAtIsNull(id);
        if (order == null) {
            throw new ApplicationException(ErrorCode.ORDER_NOT_EXISTED);
        }
        order.setLocationShipping(request.getLocationShipping());
        order.setQuantity(request.getQuantity());
        order.setPaymentMethod(request.getPaymentMethod());
        orderRepo.save(order);
        return orderMapper.toOrderDetailResponse(order);
    }



    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN') and #id.equals(authentication.name)")
    public String cancelOrder(String id) {
        Order order = orderRepo.findByIdAndDeletedAtIsNull(id);
        if (order == null) {
            throw new ApplicationException(ErrorCode.ORDER_NOT_EXISTED);
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);

        orderCanceledEventKafkaTemplate.send("canceled-order", orderMapper.toOrderCanceledEvent(order));
        return "your order is successfully canceled";
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(String id) {
        Order order = orderRepo.findByIdAndDeletedAtIsNull(id);
        if (order == null) {
            throw new ApplicationException(ErrorCode.ORDER_NOT_EXISTED);
        }
        order.setDeletedAt(LocalDateTime.now());
        orderRepo.save(order);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Page<OrderDetailResponse> getAllOrder(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return orderRepo.findByDeletedAtIsNull(pageable).map(orderMapper::toOrderDetailResponse);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','PRODUCT_MANAGER')")
    public Page<OrderResponse> getOrdersByProductId(String productId, int page, int size, String sortBy) {
        System.out.println("productId:::"+productId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return orderRepo.findByProductIdAndDeletedAtIsNull(productId, pageable).map(orderMapper::toOrderResponse);
    }

    @Override
    @PreAuthorize("(hasRole('ADMIN') or (hasRole('USER') and #userId.equals(authentication.name)))")
    public Page<OrderDetailResponse> getOrdersByUserId(String userId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return orderRepo.findByUserIdAndDeletedAtIsNull(userId, pageable).map(orderMapper::toOrderDetailResponse);
    }

    private static final String CHECK_AND_UPDATE_STOCK_SCRIPT =
            "local product_data = redis.call('GET', KEYS[1]) " +
                    "if not product_data then return -1 end " +
                    "local product = cjson.decode(product_data) " +
                    "if not product.stock then return -2 end " +
                    "local stock_number = tonumber(product.stock) " +
                    "if not stock_number then return -3 end " +
                    "local quantity = tonumber(ARGV[1]) " +
                    "if stock_number < quantity then return -1 end " +
                    "product.stock = stock_number - quantity " +
                    "redis.call('SET', KEYS[1], cjson.encode(product)) " +
                    "return product.stock";

    public boolean checkAndUpdateStock(String productId, int quantity) {
        Long result = redisTemplate.execute(new DefaultRedisScript<>(CHECK_AND_UPDATE_STOCK_SCRIPT, Long.class),
                Collections.singletonList("product:" + productId),
                quantity);

        if (result == -2) {
            throw new IllegalStateException("Stock field is missing in Redis data.");
        }
        if (result == -3) {
            throw new IllegalStateException("Stock value in Redis is invalid.");
        }
        return result >= 0;
    }
}
