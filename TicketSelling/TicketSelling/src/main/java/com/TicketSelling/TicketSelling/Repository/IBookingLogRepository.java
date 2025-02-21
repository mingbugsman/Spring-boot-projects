package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.BookingLog;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingLogRepository {
    BookingLog save(BookingLog bookingLog);
    List<BookingLog> getAllBookingLogByCustomerId(String customerId);
    List<BookingLog> findBookingLogByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    BookingLog findBookingLog(String customerId, String ticketId);
}
