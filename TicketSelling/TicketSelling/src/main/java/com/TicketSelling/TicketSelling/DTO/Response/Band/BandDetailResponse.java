package com.TicketSelling.TicketSelling.DTO.Response.Band;

import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Enum.BandStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BandDetailResponse {
    String id;
    String bandName;
    BandStatus bandStatus;
    String bandInformation;
    LocalDateTime updatedAt;
    List<ConcertResponse> concertList; // last updated
}
