package com.TicketSelling.TicketSelling.DTO.Request.Booking;


import com.TicketSelling.TicketSelling.Enum.BookingStatus;
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
public class BookingUpdateRequest {
    @NotNull(message = "Booking status is required")
    BookingStatus bookingStatus;

    List<String> ticketIds; // ticket list if needed
}
