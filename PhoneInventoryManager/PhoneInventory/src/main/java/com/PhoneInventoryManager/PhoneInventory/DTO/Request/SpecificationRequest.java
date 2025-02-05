package com.PhoneInventoryManager.PhoneInventory.DTO.Request;

import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Specification.SpecificationDTO;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecificationRequest {
    String storage;
    String ram;
    @DecimalMin("2.0")
    Double screenSize;
    private String camera;
    String battery;
    String os;

;}
