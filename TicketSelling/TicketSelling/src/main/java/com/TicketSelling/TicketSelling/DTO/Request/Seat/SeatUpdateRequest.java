package com.TicketSelling.TicketSelling.DTO.Request.Seat;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import com.TicketSelling.TicketSelling.Enum.SeatType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class SeatUpdateRequest {

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    BigDecimal price;

    String row;

    Integer seatNumber;

    SeatStatus seatStatus;
    SeatType seatType;
    String hallId;
}
