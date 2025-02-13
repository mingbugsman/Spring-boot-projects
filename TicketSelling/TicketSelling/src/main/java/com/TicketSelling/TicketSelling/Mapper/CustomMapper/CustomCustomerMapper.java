package com.TicketSelling.TicketSelling.Mapper.CustomMapper;


import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;

import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.ConcertMapper;

import com.TicketSelling.TicketSelling.Utils.SafeCastList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomCustomerMapper {
    BookingMapper bookingMapper;
    ConcertMapper concertMapper;

    public CustomerBookingsResponse toCustomerBookingsResponse(Customer customer) {
        List<BookingResponse> bookingsResponses = customer.getBookings().stream().map(bookingMapper::toBookingResponse).toList();
        return new CustomerBookingsResponse(customer.getId(), customer.getCustomerName(), bookingsResponses);
    }

    public CustomerConcertHistoryResponse toCustomerConcertHistoryResponse(Object[] data) {
        if (data == null || data.length < 3) {
            throw new ApplicationException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        List<Concert> concerts = SafeCastList.safeCastList(data[2], Concert.class);
        return new CustomerConcertHistoryResponse(
                (String) data[0],
                (String) data[1],
                concerts.stream().map(concertMapper::toConcertResponse).toList()
        );
    }
}

