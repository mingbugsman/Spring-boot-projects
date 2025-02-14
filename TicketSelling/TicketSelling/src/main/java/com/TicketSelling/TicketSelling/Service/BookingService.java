package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Enum.TypedSort;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Repository.IBookingRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    IBookingRepository bookingRepository;


    // GET
    public BookingResponse getBooking(String bookingId) {
        return bookingRepository.getBooking(bookingId);
    }

    public Booking getBookingById(String bookingId) {
        return bookingRepository.getBookingById(bookingId);
    }

    public List<BookingResponse> getAllBookings(TypedSort typedSort) {
        return bookingRepository.getAllBookings(typedSort);
    }

    // POST
    public BookingResponse createNewBooking(BookingCreationRequest request) {
        return bookingRepository.createNewBooking(request);
    }

    // PUT /PATCH
    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request) {
        return bookingRepository.updateBooking(bookingId, request);
    }

    // DELETE
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteBooking(bookingId);
    }

}
