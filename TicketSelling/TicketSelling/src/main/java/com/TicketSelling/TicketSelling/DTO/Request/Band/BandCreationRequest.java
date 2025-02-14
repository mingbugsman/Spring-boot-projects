package com.TicketSelling.TicketSelling.DTO.Request.Band;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BandCreationRequest {

    @NotBlank(message = "Band name is required")
    String bandName;

    @NotBlank(message = "Band information is required")
    String bandInformation;

    @NotBlank
    String country;

    @NotBlank
    String genre;

    @NotBlank
    Integer foundingYear;

}
