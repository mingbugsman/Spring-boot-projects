package com.PhoneInventoryManager.PhoneInventory.DTO.Request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneImageRequest {
    byte[] dataImage;
    boolean isPrimary;
}
