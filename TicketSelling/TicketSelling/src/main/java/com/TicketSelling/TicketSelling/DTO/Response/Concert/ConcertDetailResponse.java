package com.TicketSelling.TicketSelling.DTO.Response.Concert;

import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertDetailResponse {
    String id;
    LocalDateTime date;// created date event
    LocalDateTime startDate;// event start date;
    String concertName;
    ConcertStatus concertStatus;
    String hallName;
    String concertInformation;
    LocalDateTime updatedAt;// last updated
    List<BandResponse> bandDetailList;
}