package com.TicketSelling.TicketSelling.DTO.Request.Booking;

import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketCreationRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListBookingCreationRequest {
    @NotNull(message = "Customer ID is required")
    String customerId;

    @NotNull(message = "Concert ID is required")
    String concertId;

    @NotEmpty(message = "At least one seat must be selected")
    List<TicketCreationRequest> tickets;
}
