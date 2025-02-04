package com.PhoneInventoryManager.PhoneInventory.Mapper;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.SpecificationRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Specification.SpecificationDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Specification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecificationMapper {

    @Mapping(target = "phone", ignore = true)
    Specification toSpecification(SpecificationRequest request);

    SpecificationDTO toSpecificationResponse(Specification specification);

}
