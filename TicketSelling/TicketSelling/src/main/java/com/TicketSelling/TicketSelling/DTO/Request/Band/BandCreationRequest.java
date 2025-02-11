package com.TicketSelling.TicketSelling.DTO.Request.Band;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class BandCreationRequest {

    @NotBlank(message = "Band name is required")
    String bandName;

    @NotBlank(message = "Band information is required")
    String bandInformation;


}
