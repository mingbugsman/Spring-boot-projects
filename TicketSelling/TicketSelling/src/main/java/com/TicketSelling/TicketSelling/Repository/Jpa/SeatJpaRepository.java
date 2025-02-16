package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat,String> {
    boolean existsByRowNumberAndSeatNumberAndHallId(Integer rowNumber, Integer seatNumber, String hallId);

    @Query("SELECT s FROM Concert c " +
            "JOIN c.hall h " +
            "JOIN h.seats s " +
            "WHERE c.id = :concertId AND c.deletedAt IS NULL AND h.deletedAt IS NULL AND s.deletedAt IS NULL")
    List<Seat> getAllSeatsByConcertId(@Param("concertId") String concertId);

    @Query("SELECT s FROM Seat s " +
            "WHERE s.deletedAt IS NULL")
    List<Seat> getAllSeats();
}
