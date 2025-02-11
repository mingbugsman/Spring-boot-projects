package com.TicketSelling.TicketSelling.DTO.Request.Ticket;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketCreationRequest {

    @NotNull(message = "Seat ID is required")
    String seatId;


    @NotNull(message = "Concert ID is required")
    String concertId;

    @NotNull(message = "Booking ID is required")
    String bookingId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
}
