package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.ListBookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Entity.*;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomBookingMapper;
import com.TicketSelling.TicketSelling.Mapper.TicketMapper;
import com.TicketSelling.TicketSelling.Repository.*;

import com.TicketSelling.TicketSelling.Utils.SortUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    IConcertRepository concertRepository;
    ITicketRepository ticketRepository;
    ICustomerRepository customerRepository;
    IBookingRepository bookingRepository;
    ISeatRepository seatRepository;
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

    public List<BookingResponse> getAllBookingsByCustomerId(String customerId, LocalDateTime lastCreatedAt,
                                                SortOrder sortOrder, int pageSize) {
        Sort.Direction direction = sortOrder.toSpringSortOrder();
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(direction, "createdAt"));
        return bookingRepository.getBookingsByCustomerId(customerId, lastCreatedAt, sortOrder.name(), pageable).stream().map(bookingMapper::toBookingResponse).collect(Collectors.toList());
    }

    // POST
    public BookingTicketsResponse createNewBooking(String concertId, BookingCreationRequest request) {
        Concert concert = concertRepository.findConcertById(concertId);
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        Booking booking = bookingMapper.toBooking(request);
        booking.setCustomer(customer);
        execSavingBookingWithTickets(request, booking, concert);
        return customBookingMapper.toBookingTicketsResponse(booking);
    }


    public List<BookingTicketsResponse> createNewListBooking (
            List<ListBookingCreationRequest> requests
    ) {
        List<BookingTicketsResponse> bookingTicketsResponses = new ArrayList<>();
        for (var req : requests) {
            BookingCreationRequest requestCreation = BookingCreationRequest.builder()
                    .customerId(req.getCustomerId())
                    .tickets(req.getTickets())
                    .build();
            BookingTicketsResponse bookingTicketsResponse = createNewBooking(req.getConcertId(),requestCreation);
            bookingTicketsResponses.add(bookingTicketsResponse);
        }
        return bookingTicketsResponses;
    }

    // PUT /PATCH
    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        bookingMapper.updateBooking(booking, request);
        if (request.getTicketIds() != null ) {
            List<TicketPK> ticketIds = request.getTicketIds();
            for (var ticketId :ticketIds) {
                Ticket ticket = ticketRepository.findTicketById(ticketId);
                booking.getTickets().add(ticket);
            }
        }

        return bookingMapper.toBookingResponse(booking);
    }

    // DELETE
    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        bookingRepository.deleteBooking(booking);
    }

    private void execSavingBookingWithTickets(BookingCreationRequest request, Booking booking, Concert concert) {
        if (request.getTickets() != null) {
            List<Ticket> tickets = new ArrayList<>();
            for ( var ticketCreationRequest : request.getTickets()) {
                Seat seat = seatRepository.findSeatById(ticketCreationRequest.getSeatId());
                seat.setSeatStatus(SeatStatus.RESERVED);
                TicketPK ticketPK = new TicketPK(seat.getId(), concert.getId());
                Ticket ticket = Ticket.builder()
                        .id(ticketPK)
                        .booking(booking)
                        .concert(concert)
                        .price(seat.getSeatCategory().getPrice())
                        .seat(seat)
                        .build();
                seatRepository.save(seat);
                tickets.add(ticket);
            }
            booking.setTickets(tickets);
            bookingRepository.save(booking);
            ticketRepository.saveAll(tickets);
        }
    }


}
