package com.PhoneInventoryManager.PhoneInventory.Mapper;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneSummaryDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    default PhoneSummaryDTO toPHONE_SUMMARY_DTO(Object[] data) {
        if (data == null) {
            return null;
        }
        String categoryName, model, os, brand, imageData;
        categoryName = model = brand = imageData = os = null;
        if (data.length >= 5) {
                 categoryName = (String) data[0]; // c.name
                 model = (String) data[1]; // p.model
                 os = (String) data[2]; // s.os
                 brand = (String) data[3]; // p.brand
                 imageData = Base64.getEncoder().encodeToString((byte[])data[4]); // pi.data_image
            }

        return new PhoneSummaryDTO(categoryName, model, os, brand, imageData);
    }

}
