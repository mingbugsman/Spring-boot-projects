package com.TicketSelling.TicketSelling.DTO.Request.Band;

import com.TicketSelling.TicketSelling.Enum.BandStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BandUpdateRequest {
    String bandName;
    String bandInformation;
    BandStatus bandStatus;
    List<String> concertId;
}
