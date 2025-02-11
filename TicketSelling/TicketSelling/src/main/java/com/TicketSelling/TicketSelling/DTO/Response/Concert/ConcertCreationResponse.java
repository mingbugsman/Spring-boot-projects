package com.TicketSelling.TicketSelling.DTO.Response.Concert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertCreationResponse {
    String id;
    String concertName;
    LocalDateTime date; // created date event
    LocalDateTime startDate; // event start
}
