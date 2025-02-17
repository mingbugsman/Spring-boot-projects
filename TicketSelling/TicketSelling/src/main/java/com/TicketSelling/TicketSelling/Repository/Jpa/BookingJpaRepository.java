package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingJpaRepository extends JpaRepository<Booking, String> {

    @Query("""
        SELECT b FROM Booking b
        WHERE b.customer.id = :customerId AND b.deletedAt IS NULL
        AND (:lastCreatedAt IS NULL OR \s
            (:sortDirection = 'ASC' AND b.createdAt > :lastCreatedAt) OR
            (:sortDirection = 'DESC' AND b.createdAt < :lastCreatedAt))
        ORDER BY \s
            CASE WHEN :sortDirection = 'ASC' THEN b.createdAt END ASC,
            CASE WHEN :sortDirection = 'DESC' THEN b.createdAt END DESC
        """)
    List<Booking> getBookingsByCustomerId(
            @Param("customerId") String customerId,
            @Param("lastCreatedAt") LocalDateTime lastCreatedAt,
            @Param("sortDirection") String sortDirection,
            Pageable pageable
    );

    @Query("SELECT b from Booking b " +
            "WHERE b.deletedAt IS NULL")
    List<Booking> getAllBookings();

    @Query("SELECT b from Booking b " +
            "WHERE b.deletedAt IS NULL AND b.id = :bookingId")
    Optional<Booking> findBookingById(@Param("bookingId") String bookingId);

}
