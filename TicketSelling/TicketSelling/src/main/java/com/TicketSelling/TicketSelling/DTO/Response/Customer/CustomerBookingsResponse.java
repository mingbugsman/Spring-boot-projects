package com.TicketSelling.TicketSelling.DTO.Response.Customer;


import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
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
public class CustomerBookingsResponse {
    String id;
    String name;
    List<BookingResponse> bookings;
}
