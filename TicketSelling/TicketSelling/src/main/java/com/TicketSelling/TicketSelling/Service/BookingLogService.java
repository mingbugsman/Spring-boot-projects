package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.BookingLog.BookingLogRequest;
import com.TicketSelling.TicketSelling.Entity.BookingLog;
import com.TicketSelling.TicketSelling.Enum.BookingLogAction;
import com.TicketSelling.TicketSelling.Repository.IBookingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor

public class BookingLogService {

    private  final IBookingLogRepository bookingLogRepository;

    public void createNewBookingLog(String customerId, String ticketId, BookingLogRequest bookingLogRequest) {
        BookingLog bookingLog = BookingLog.builder()
                .customerId(customerId)
                .ticketId(ticketId)
                .action(BookingLogAction.BOOKING)
                .build();
        bookingLogRepository.save(bookingLog);
    }
    public void updateBookingLog(String customerId, String ticketId, BookingLogRequest request) {
        BookingLog bookingLog = bookingLogRepository.findBookingLog(customerId, ticketId);
        bookingLog.setAction(request.getAction());
     //   bookingLog.setDetails(request.getClass());
        bookingLogRepository.save(bookingLog);
    }

    public List<BookingLog> getAllBookingLogByCustomerId(String customerId) {
        return bookingLogRepository.getAllBookingLogByCustomerId(customerId);
    }
    public List<BookingLog> findBookingLogByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingLogRepository.findBookingLogByDateRange(startDate,endDate);
    }
    private String createLogDetail(BookingLogRequest request) {
        return "";
    }
}

