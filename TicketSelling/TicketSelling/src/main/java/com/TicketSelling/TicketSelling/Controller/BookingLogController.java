package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.Entity.BookingLog;
import com.TicketSelling.TicketSelling.Service.BookingLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/log/booking")
@RequiredArgsConstructor
public class BookingLogController {
    BookingLogService bookingLogService;


    @GetMapping("/{customerId}")
    public ApiResponse<List<BookingLog>> getBookingLogByCustomerId(@PathVariable String customerId) {
        return ApiResponse.<List<BookingLog>>builder()
                .result(bookingLogService.getAllBookingLogByCustomerId(customerId))
                .build();
    }
}
