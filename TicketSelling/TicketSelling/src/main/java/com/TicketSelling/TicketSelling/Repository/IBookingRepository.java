package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface IBookingRepository {
    Booking save(Booking booking);
    Booking getBookingById(String bookingId);
    List<Booking> getBookingsByCustomerId(
             String customerId,
            LocalDateTime lastCreatedAt,
            String sortDirection,
            Pageable pageable
    );
    List<Booking> getAllBookings();
    void deleteBooking(Booking booking);
    boolean existsById(String bookingId);
}
