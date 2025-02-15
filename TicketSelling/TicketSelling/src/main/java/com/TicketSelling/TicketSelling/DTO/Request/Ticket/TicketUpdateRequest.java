package com.TicketSelling.TicketSelling.DTO.Request.Ticket;

import com.TicketSelling.TicketSelling.Enum.TicketStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class TicketUpdateRequest {



    @NotNull(message = "Booking ID is required")
    String bookingId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    BigDecimal price;

    @NotNull(message = "Ticket status is required")
    TicketStatus ticketStatus;

}
