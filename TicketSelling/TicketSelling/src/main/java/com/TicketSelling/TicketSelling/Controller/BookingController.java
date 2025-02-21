package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.ListBookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Service.BookingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @GetMapping
    public ApiResponse<List<BookingResponse>> getAllBookings(
            @RequestParam(required = false) SortOrder typedSort) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAllBookings(typedSort))
                .build();
    }

    @GetMapping("/by-customer/{customerId}")
    public ApiResponse<List<BookingResponse>> getAllBookingsByCustomerId(
            @PathVariable String customerId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAllBookingsByCustomerId(customerId, lastCreatedAt, sortOrder, pageSize))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookingTicketsResponse> getBookingById(@PathVariable String id) {
        return ApiResponse.<BookingTicketsResponse>builder()
                .result(bookingService.getBookingById(id))
                .build();
    }

    @PostMapping("/{concertId}")
    public ApiResponse<BookingTicketsResponse> createNewBooking(
            @PathVariable String concertId,
            @Valid @RequestBody BookingCreationRequest request) {
        return ApiResponse.<BookingTicketsResponse>builder()
                .result(bookingService.createNewBooking(concertId,request))
                .build();
    }

    @PostMapping("/list")
    public ApiResponse<List<BookingTicketsResponse>> createNewListBooking (
            @Valid @RequestBody List<ListBookingCreationRequest> requests
    ) {
        return ApiResponse.<List<BookingTicketsResponse>>builder()
                .result(bookingService.createNewListBooking(requests))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> updateBooking(
            @PathVariable String id, @Valid @RequestBody BookingUpdateRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ApiResponse.<String>builder()
                .message("Booking with ID " + id + " is successfully deleted")
                .build();
    }
}
