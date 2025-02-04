package com.PhoneInventoryManager.PhoneInventory.Repository;

import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {

    boolean existsByModelAndBrand(String model, String brand);

    //Get the phone with specifications and catalog (JOIN FETCH)
    @Query("SELECT p FROM Phone p " +
            "LEFT JOIN FETCH p.specification " +
            "LEFT JOIN FETCH p.category " +
            "WHERE p.id = :id")
    List<Phone> findPhoneWithDetails(@Param("id") String id);

    // find phone with catalog and price range
    @Query("SELECT p from Phone p " +
            "JOIN p.category c " +
            "Where c.name = :categoryName " +
            "AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Phone> findByCategoryAndPriceRange(
            @Param("categoryName") String categoryName,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

    // Inventory statistics by category (GROUP BY + JOIN)
    @Query("SELECT c.name AS category, SUM(p.stockQuantity) AS totalStock " +
            "FROM Phone p " +
            "JOIN p.category c " +
            "GROUP BY c.name")
    List<Object[]> getStockByCategory();
}
