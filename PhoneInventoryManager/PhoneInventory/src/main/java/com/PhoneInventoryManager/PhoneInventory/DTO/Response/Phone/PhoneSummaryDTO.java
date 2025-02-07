package com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneSummaryDTO {
    private String CategoryName;
    private String model;
    private String os;
    private String brand;
    private String dataImage; // base64
}
