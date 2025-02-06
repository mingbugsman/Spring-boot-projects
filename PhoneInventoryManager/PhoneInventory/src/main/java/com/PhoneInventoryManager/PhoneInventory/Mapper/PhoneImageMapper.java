package com.PhoneInventoryManager.PhoneInventory.Mapper;

import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneImageRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.PhoneImage.PhoneImageDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.PhoneImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface PhoneImageMapper {

    @Mapping(target = "dataImage", source = "dataImage")  // Ánh xạ từ dataImage của PhoneImageRequest sang PhoneImage
    @Mapping(target = "phone", ignore = true)
    PhoneImage toPhoneImage(PhoneImageRequest request);

    default PhoneImageDTO toPhoneImageDTO(PhoneImage phoneImage) {
        return PhoneImageDTO.builder()
                .id(phoneImage.getId())
                .isPrimary(phoneImage.isPrimary())
                .dataImage(Base64.getEncoder().encodeToString(phoneImage.getDataImage()))
                .build();
    }
}
