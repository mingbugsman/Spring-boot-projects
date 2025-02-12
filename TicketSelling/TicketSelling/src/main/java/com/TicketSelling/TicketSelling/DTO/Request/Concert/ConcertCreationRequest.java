package com.TicketSelling.TicketSelling.DTO.Request.Concert;

import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertCreationRequest {

    @NotNull( message = "Event Start Date is required")
    LocalDateTime startDate; // event start date;

    @NotNull(message = "Concert name is required")
    String concertName;

    String concertInformation;
}
