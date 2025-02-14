package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import com.TicketSelling.TicketSelling.Enum.TypedSort;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomBookingMapper;
import com.TicketSelling.TicketSelling.Repository.IBookingRepository;

import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    ITicketRepository ticketRepository;
    ICustomerRepository customerRepository;
    IBookingRepository bookingRepository;
    BookingMapper bookingMapper;
    CustomBookingMapper customBookingMapper;

    // GET

    public BookingTicketsResponse getBookingById(String bookingId) {
        return customBookingMapper.toBookingTicketsResponse(
                bookingRepository.getBookingById(bookingId)
        );
    }

    public List<BookingResponse> getAllBookings(TypedSort typedSort) {
        return bookingRepository.getAllBookings(typedSort).stream().map(bookingMapper::toBookingResponse).toList();
    }

    // POST
    public BookingResponse createNewBooking(BookingCreationRequest request) {
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        Booking booking = bookingMapper.toBooking(request);
        booking.setCustomer(customer);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));

    }

    // PUT /PATCH
    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        bookingMapper.updateBooking(booking, request);
        List<TicketPK> ticketIds = request.getTicketIds();
        for (var ticketId :ticketIds) {
            Ticket ticket = ticketRepository.findTicketById(ticketId);
            booking.getTickets().add(ticket);
        }
        return bookingMapper.toBookingResponse(booking);
    }

    // DELETE
    public void deleteBooking(String bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteBookingById(bookingId);
        } else {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
    }

}
