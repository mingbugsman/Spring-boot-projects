package com.ZZZZ.ProductService;

import com.ZZZZ.ProductService.DTO.request.ProductCreationRequest;
import com.ZZZZ.ProductService.DTO.request.ProductUpdateRequest;
import com.ZZZZ.ProductService.DTO.response.product.ProductResponse;
import com.ZZZZ.ProductService.entity.Product;
import com.ZZZZ.ProductService.repository.jpa.ProductJpaRepo;
import com.ZZZZ.ProductService.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductJpaRepo productJpaRepo;

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Test
    public void createProduct() {
        ProductCreationRequest request = new ProductCreationRequest("The eyes", "The greatest book of all time", 10000000, 150);
        ProductResponse response = productService.createProduct(request);
        assertNotNull(response);
        assertEquals("The eyes", response.name());
        assertEquals(150, response.price());
    }

    @Test
    public void deleteProduct() {
        String productId = "93e9d4df-0285-4624-82b2-60ae3ea24b1e";
        productService.deleteProduct(productId);
        assertThrows(RuntimeException.class, () -> productService.getProduct(productId));
    }

    @Test
    public void getAllProducts_ShouldReturnPagedResults() {
        Page<ProductResponse> page = productService.getAllProducts(0, 2, "stock");
        assertNotNull(page);
        assertEquals(2, page.getContent().size());
        assertTrue(page.getTotalElements() >= 3);
    }


    @Test
    public void updateProduct_ShouldUpdateStockAndCache() {
        // Arrange
        Product product = new Product();
        product.setName("Keyboard");
        product.setDescription("keyboard is vip pro");
        product.setPrice(100000);
        product.setStock(10);
        product.setDeleteAt(null);
        product.setUpdateAt(null);
        productJpaRepo.save(product);


        ProductUpdateRequest request = new ProductUpdateRequest();
        request.setStock(5);
        request.setPrice(product.getPrice());

        // Act
        ProductResponse response = productService.updateProduct(product.getId(), request);

        // Assert
        assertNotNull(response);
        assertEquals(5, response.stock());

        // Verify cache update
        Product cachedProduct = redisTemplate.opsForValue().get("product:" + product.getId());
        assertNotNull(cachedProduct);
        assertEquals(5, cachedProduct.getStock());
    }
}
