package com.TicketSelling.TicketSelling.DTO.Request.Booking;



import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketCreationRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookingCreationRequest {

    @NotNull(message = "Customer ID is required")
    String customerId;

    @NotEmpty(message = "At least one seat must be selected")
    List<TicketCreationRequest> tickets;
}

