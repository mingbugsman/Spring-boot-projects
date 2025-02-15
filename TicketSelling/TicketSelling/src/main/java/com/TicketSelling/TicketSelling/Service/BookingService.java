package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomBookingMapper;
import com.TicketSelling.TicketSelling.Repository.IBookingRepository;

import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import com.TicketSelling.TicketSelling.Utils.SortUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<BookingResponse> getAllBookings(SortOrder typedSort) {
        List<Booking> sortedBooking = SortUtils.sortList(bookingRepository.getAllBookings(),SortOrder.DESC, Booking::getCreatedAt);
        return sortedBooking.stream().map(bookingMapper::toBookingResponse).toList();
    }

    public List<BookingResponse> getAllBookings(String customerId, LocalDateTime lastCreatedAt,
                                                SortOrder sortOrder, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);
        return bookingRepository.getBookingsByCustomerId(customerId, lastCreatedAt, sortOrder.name(), pageable).stream().map(bookingMapper::toBookingResponse).collect(Collectors.toList());
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
        Booking booking = bookingRepository.getBookingById(bookingId);
        bookingRepository.deleteBooking(booking);
    }

}
