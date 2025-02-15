package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingJpaRepository extends JpaRepository<Booking, String> {

    @Query("""
            SELECT c FROM Customer c
            JOIN c.bookings
            WHERE c.id = :customerId
            AND ( :lastCreatedAt IS NULL OR \s
                ( :sortDirection = 'ASC' AND c.createdAt > :lastCreatedAt) OR \s
                (:sortDirection = 'DESC' AND c.createdAt < :lastCreatedAt))
            
            ORDER BY c.createdAt :sortDirection
            \s""")
    List<Booking> getBookingsByCustomerId(
            @Param("customerId") String customerId,
            @Param("lastCreatedAt") LocalDateTime lastCreatedAt,
            @Param("sortDirection") String sortDirection,
            Pageable pageable
            );
}
