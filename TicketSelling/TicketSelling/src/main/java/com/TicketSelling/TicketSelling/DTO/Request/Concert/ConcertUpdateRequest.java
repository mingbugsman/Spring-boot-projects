package com.TicketSelling.TicketSelling.DTO.Request.Concert;

import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import jakarta.validation.constraints.NotBlank;
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
public class ConcertUpdateRequest {
    String concertName;
    String concertInformation;
    LocalDateTime startDate; // event start date
    ConcertStatus concertStatus;
    String hallId;
    List<String> bandId;
}
