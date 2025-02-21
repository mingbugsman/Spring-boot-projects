package com.TicketSelling.TicketSelling.Mapper.CustomMapper;


import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Mapper.TicketMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomBookingMapper {


    TicketMapper ticketMapper;
    public BookingTicketsResponse toBookingTicketsResponse(Booking booking) {
        return new BookingTicketsResponse(
                booking.getId(),
                booking.getCustomer().getId(),
                booking.getCustomer().getCustomerName(),
                booking.getBookingStatus(),
                booking.getCreatedAt(),
                booking.getUpdatedAt(),
                booking.getTickets().stream().map(ticketMapper::toTicketResponse).toList()
        );
    }

}
