package com.TicketSelling.TicketSelling.DTO.Request.Band;

import com.TicketSelling.TicketSelling.Enum.BandStatus;

import java.util.List;

public class BandUpdateRequest {
    String bandName;
    String bandInformation;
    BandStatus bandStatus;
    List<String> concertId;
}
