package com.TicketSelling.TicketSelling.DTO.Request.Concert;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcertUpdateBands {
    @NotNull(message = "concertId is required")
    String concertId;
    @NotNull(message = "BandIds is required")
    List<String> bandIds;
}
