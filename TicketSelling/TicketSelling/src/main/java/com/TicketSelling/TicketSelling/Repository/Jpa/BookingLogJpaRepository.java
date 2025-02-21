package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.BookingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingLogJpaRepository extends JpaRepository<BookingLog, String> {

    @Query("SELECT b FROM BookingLog b " +
            "FROM b.customerId = :customerId")
    List<BookingLog> getAllBookingLogByCustomerId(@Param("customerId") String customerId);

    List<BookingLog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    BookingLog findByTicketIdAndCustomerId(String ticketId, String customerId);
}
