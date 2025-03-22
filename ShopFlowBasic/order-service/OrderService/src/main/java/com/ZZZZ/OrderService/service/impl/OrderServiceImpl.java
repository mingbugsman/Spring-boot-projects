package com.ZZZZ.OrderService.service.impl;

import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.request.OrderUpdateRequest;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.OrderService.kafka.OrderEvent;
import com.ZZZZ.OrderService.kafka.OrderEventProducer;
import com.ZZZZ.OrderService.mapper.OrderMapper;
import com.ZZZZ.OrderService.repository.OrderRepo;
import com.ZZZZ.OrderService.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderMapper orderMapper;
    OrderRepo orderRepo;
    OrderEventProducer producer;
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public OrderResponse createOrder(OrderCreationRequest request) {
        boolean stockUpdated = checkAndUpdateStock(request.getProductId(), request.getQuantity());
        if (!stockUpdated) {

            producer.sendOrderCreatedEvent("out of stock");
            throw new RuntimeException("Not enough stock");
        }

        // Mapping và lưu order
        Order order = orderMapper.toOrder(request);
        orderRepo.save(order);

        // Gửi sự kiện thành công
        //OrderEvent successEvent = orderMapper.toOrderEvent(order, "order-created");
        producer.sendOrderCreatedEvent(order.getProductId());

        return orderMapper.toOrderResponse(order);
    }


    @Override
    public OrderResponse updateInformationOrder(OrderUpdateRequest request) {
        return null;
    }

    @Override
    public String cancelOrder(String id) {
        return "";
    }

    @Override
    public void deleteOrder(String id) {

    }

    @Override
    public Page<OrderResponse> getAllOrder(int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<OrderResponse> getOrdersByProductId(String productId, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<OrderResponse> getOrdersByUserId(String userId, int page, int size, String sortBy) {
        return null;
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
