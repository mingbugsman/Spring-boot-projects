package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.Entity.BookingLog;
import com.TicketSelling.TicketSelling.Repository.IBookingLogRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.BookingLogJpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingLogRepositoryImp implements IBookingLogRepository {
    BookingLogJpaRepository bookingLogJpaRepository;
    @Override
    public BookingLog save(BookingLog bookingLog) {
        return bookingLogJpaRepository.save(bookingLog);
    }

    @Override
    public List<BookingLog> getAllBookingLogByCustomerId(String customerId) {
        return bookingLogJpaRepository.getAllBookingLogByCustomerId(customerId);
    }

    @Override
    public List<BookingLog> findBookingLogByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingLogJpaRepository.findByCreatedAtBetween(startDate,endDate);
    }

    @Override
    public BookingLog findBookingLog(String customerId, String ticketId) {
        return bookingLogJpaRepository.findByTicketIdAndCustomerId(customerId, ticketId);
    }
}
