package com.PhoneInventoryManager.PhoneInventory.Repository;

import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryStockDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface PhoneCategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);

    @Query("SELECT c.name, COUNT(p.id) AS phoneCount " +
            "FROM Category c " +
            "LEFT JOIN c.phones p "  +
            "GROUP BY c.name")
    List<Object[]> getCategoriesWithPhoneCount();

    @Query("SELECT c.name, COUNT(p.id) " +
            "FROM Category c " +
            "JOIN c.phones p " +
            "WHERE c.name = :categoryName " +
            "GROUP BY c.name")
    Object getCategoryWithPhoneCount(@Param("categoryName") String categoryName);



    // Top three popular categories
    @Query("SELECT c.name, COUNT(p.id) AS phoneCount " +
            "FROM Category c " +
            "JOIN c.phones p " +
            "GROUP BY c.name " +
            "ORDER BY phoneCount DESC " +
            "LIMIT 3")
    List<Object[]> findPopularCategories();

    // Inventory statistics by category (GROUP BY + JOIN)
    @Query("SELECT c.name AS category, SUM(p.stockQuantity) AS totalStock " +
            "FROM Phone p " +
            "JOIN p.category c " +
            "GROUP BY c.name")
    List<Object[]> getStockByCategory();
}
