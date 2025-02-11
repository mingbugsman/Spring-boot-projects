package com.TicketSelling.TicketSelling.DTO.Response.Concert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertDetailResponse {
    String id;
    LocalDateTime date; // created date event
    LocalDateTime startDate; // event start date;
    String hallName;
    String bandName;
}
