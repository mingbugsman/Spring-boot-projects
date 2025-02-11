package com.TicketSelling.TicketSelling.DTO.Request.Seat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatCreationRequest {
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    BigDecimal price;

    @NotNull(message = "row number is required")
    @PositiveOrZero
    Integer rowNumber;


    @NotNull(message = "seat number is required")
    @PositiveOrZero
    Integer seatNumber;

    @NotNull(message = "Hall Id is required")
    String hallId;
}
