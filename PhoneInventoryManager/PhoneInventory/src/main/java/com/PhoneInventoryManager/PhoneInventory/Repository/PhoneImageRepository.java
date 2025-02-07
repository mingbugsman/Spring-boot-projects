package com.PhoneInventoryManager.PhoneInventory.Repository;

import com.PhoneInventoryManager.PhoneInventory.Entity.PhoneImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PhoneImageRepository extends JpaRepository<PhoneImage,String> {
    List<PhoneImage> findByPhoneId(String phoneId);
}
