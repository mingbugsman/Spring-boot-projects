package com.PhoneInventoryManager.PhoneInventory.DTO.Response.Specification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecificationDTO {
     String id;
     String storage;
     String ram;
     Double screenSize;
     String camera;
     String battery;
     String os;
}
