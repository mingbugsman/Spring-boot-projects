package com.PhoneInventoryManager.PhoneInventory.Mapper;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SpecificationMapper.class)
public interface PhoneMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(source = "specification", target = "specification")
    Phone toPhone (PhoneRequest request);

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "specification", target = "specification")
    @Mapping(target = "images", ignore = true)
    PhoneDTO toPhoneResponse(Phone phone);

}
