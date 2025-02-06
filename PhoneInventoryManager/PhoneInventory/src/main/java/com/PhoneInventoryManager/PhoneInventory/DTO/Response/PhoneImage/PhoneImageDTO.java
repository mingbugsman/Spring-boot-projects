package com.PhoneInventoryManager.PhoneInventory.DTO.Response.PhoneImage;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneImageDTO {
    String id;
    String dataImage; // base64
    boolean isPrimary;
}
