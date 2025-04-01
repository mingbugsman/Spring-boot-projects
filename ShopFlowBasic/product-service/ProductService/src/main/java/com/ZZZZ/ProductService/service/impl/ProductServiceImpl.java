package com.ZZZZ.ProductService.service.impl;


import com.ZZZZ.ProductService.base.exception.ApplicationException;
import com.ZZZZ.ProductService.base.exception.ErrorCode;
import com.ZZZZ.commonDTO.Enum.EventType;
import com.ZZZZ.commonDTO.Product.ProductEvent;
import com.ZZZZ.ProductService.kafka.ProductEventProducer;
import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.request.ProductUpdateRequest;
import com.ZZZZ.ProductService.DTO.response.product.ProductResponse;
import com.ZZZZ.ProductService.entity.Product;
import com.ZZZZ.ProductService.mapper.ProductMapper;
import com.ZZZZ.ProductService.repository.ProductRepo;
import com.ZZZZ.ProductService.service.ProductService;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepo productRepo;
    ProductMapper productMapper;
    RedisTemplate<String, Product> redisTemplate;
    ProductEventProducer productEventProducer;

    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        Product product = productMapper.toProduct(request);
        productRepo.save(product);

        // caching product
        redisTemplate.opsForValue().set("product:" + product.getId(), product);
        ProductEvent productEvent = new ProductEvent(product.getId(), EventType.CREATED, "New product added: " + product.getName());
        productEventProducer.sendProductEvent(productEvent);
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse getProduct(String id) {
        // get cached product
        Product cachedProduct = redisTemplate.opsForValue().get("product:"+id);
        if (cachedProduct != null) return productMapper.toProductResponse(cachedProduct);

        // if it doesn't exist in caching product, checking in db
        Product product = productRepo.getProduct(id);
        if (product == null) {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return productRepo.getAll(pageable).map(productMapper::toProductResponse);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(String id, ProductUpdateRequest request) {
        Product product = productRepo.getProduct(id);
        if (product == null) {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        productMapper.updateProduct(product, request);
        product = productRepo.save(product);
        redisTemplate.opsForValue().set("product:" + id, product);

        if (product.getStock() == 0) {
            ProductEvent event = new ProductEvent(product.getId(), EventType.OUT_OF_STOCK, "Product is out of stock: " + product.getName());
            productEventProducer.sendProductEvent(event);
        }

        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepo.getProduct(id);
        if (product == null) {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        productRepo.softDelete(product);
        redisTemplate.delete("product:"+id);
        productEventProducer.sendDeletedProductEvent(productMapper.toDeletedProduct(product));
    }
}
