package com.TicketSelling.TicketSelling.DTO.Request.Hall;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallCreationRequest {
    @NotBlank(message = "Hall name is required")
    String hallName;

    @NotBlank(message = "Hall address is required")
    String address;

    @NotBlank(message = "Hall information is required")
    String hallInformation;

}
