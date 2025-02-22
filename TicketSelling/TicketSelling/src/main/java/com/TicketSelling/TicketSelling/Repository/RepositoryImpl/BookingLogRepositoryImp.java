package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.Entity.BookingLog;
import com.TicketSelling.TicketSelling.Repository.IBookingLogRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.BookingLogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingLogRepositoryImp implements IBookingLogRepository {
    private final BookingLogJpaRepository bookingLogJpaRepository;
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
    public BookingLog findBookingLog(String customerId, String bookingId) {
        return bookingLogJpaRepository.findByBookingIdAndCustomerId(customerId, bookingId);
    }
}
