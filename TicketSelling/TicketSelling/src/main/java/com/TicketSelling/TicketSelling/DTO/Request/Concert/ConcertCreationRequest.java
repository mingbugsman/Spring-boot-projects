package com.TicketSelling.TicketSelling.DTO.Request.Concert;

import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertCreationRequest {
    LocalDateTime date; // created date event
    LocalDateTime startDate; // event start date;
    String concertName;
    ConcertStatus concertStatus;
}
