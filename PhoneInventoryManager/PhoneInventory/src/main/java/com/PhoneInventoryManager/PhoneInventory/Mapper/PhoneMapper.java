package com.PhoneInventoryManager.PhoneInventory.Mapper;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = SpecificationMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhoneMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(source = "specification", target = "specification")
    Phone toPhone (PhoneRequest request);

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "specification", target = "specification")
    @Mapping(target = "images", ignore = true)
    PhoneDTO toPhoneResponse(Phone phone);


    @Mapping(target = "category", ignore = true)
    @Mapping(target = "specification", ignore = true)
    void updatePhone(@MappingTarget Phone phone, PhoneRequest request);

}
