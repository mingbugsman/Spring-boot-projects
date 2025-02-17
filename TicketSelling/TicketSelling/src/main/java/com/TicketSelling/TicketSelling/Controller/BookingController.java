package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
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
@RequestMapping("/api/booking")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;


    @GetMapping
    public ApiResponse<List<BookingResponse>> getAllBookings(
         @RequestParam(required = false) SortOrder typedSort) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAllBookings(typedSort)).build();
    }

    @GetMapping("/{customerId}/list")
    public ApiResponse<List<BookingResponse>> getAllBookingsByCustomerId(
            @PathVariable String customerId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder
            ) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAllBookingsByCustomerId(customerId,lastCreatedAt,sortOrder,pageSize))
                .build();
    }

    @GetMapping("/{bookingId}")
    public ApiResponse<BookingTicketsResponse> getBookingById(@PathVariable String bookingId) {
        return ApiResponse.<BookingTicketsResponse>builder()
                .result(bookingService.getBookingById(bookingId))
                .build() ;
    };

    @PostMapping
    public ApiResponse<BookingTicketsResponse> createNewBooking(
            @Valid @RequestBody BookingCreationRequest request) {
        return ApiResponse.<BookingTicketsResponse>builder()
                .result(bookingService.createNewBooking(request))
                .build();
    }

    @PutMapping("/{bookingId}")
    public ApiResponse<BookingResponse> createNewBooking(@PathVariable String bookingId,@Valid @RequestBody BookingUpdateRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(bookingId,request))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    public ApiResponse<String> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ApiResponse.<String>builder()
                .result(bookingId+" is successfully deleted").build();
    }
}

