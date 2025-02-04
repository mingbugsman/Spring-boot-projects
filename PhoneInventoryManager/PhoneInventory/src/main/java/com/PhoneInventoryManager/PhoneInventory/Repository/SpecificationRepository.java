package com.PhoneInventoryManager.PhoneInventory.Repository;

import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import com.PhoneInventoryManager.PhoneInventory.Entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, String> {

    @Query( "SELECT s From Specification s " +
            "JOIN s.phone p " +
            "WHERE s.ram >= :minRam " +
            "AND s.os = :osType")
    List<Specification> findByRamAndOs(
            @Param("minRam") String minRam,
            @Param("osType") String osType
    );

    @Query("SELECT s.battery, AVG(p.price) " +
            "FROM Specification s " +
            "JOIN s.phone p " +
            "GROUP BY s.battery")
    List<Object[]> getAveragePriceByBattery();

}
