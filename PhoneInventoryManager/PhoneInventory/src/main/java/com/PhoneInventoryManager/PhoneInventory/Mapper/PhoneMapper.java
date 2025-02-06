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

    /**
     *
     * @param request for retrieving data
     * @return {@code PHONE} After mapping all attributes from request to phone
     */
    @Mapping(target = "category", ignore = true)
    @Mapping(source = "specification", target = "specification")
    @Mapping(target = "images", ignore = true)
    Phone toPhone (PhoneRequest request);


    /**
     * <h5>The mapping ignores soldQuantity because the default is 0.</h5>
     * <h5> The mapping from the specifications to the Phone specification requirements is done by SpecificationMapper class.</h5>
     * @param request for created phone information
     * @return {@code PHONE} After mapping all attributes from request to phone
     */
    @Mapping(target = "category", ignore = true)
    @Mapping(source = "specification", target = "specification")
    @Mapping(target = "soldQuantity", ignore = true)
    @Mapping(target = "images", ignore = true)
    Phone toCreationPhone (PhoneRequest request);


    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "specification", target = "specification")
    @Mapping(target = "images", ignore = true)
    PhoneDTO toPhoneResponse(Phone phone);


    /**
     *
     * @param phone FOR THE TARGET WHICH THE USER WANT TO UPDATE
     * @param request FOR UPDATED PHONE INFORMATION
     */
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "specification", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updatePhone(@MappingTarget Phone phone, PhoneRequest request);

}
