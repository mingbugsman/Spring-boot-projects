package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat,String> {
    boolean existsBySeatNumberAndSeatCategoryId( Integer seatNumber,  String seatCategoryId );

    @Query("SELECT s FROM SeatCategory sc " +
            "JOIN sc.seats s " +
            "WHERE sc.id = :seatCategoryId AND s.deletedAt IS NULL AND sc.deletedAt IS NULL")
    List<Seat> getAllSeatsBySeatCategoryId(@Param("seatCategoryId") String seatCategoryId);

    @Query("SELECT s FROM Seat s " +
            "WHERE s.deletedAt IS NULL")
    List<Seat> getAllSeats();

    @Query("SELECT s FROM Seat s " +
            "WHERE s.deletedAt IS NULL AND s.id = :seatId")
    Optional<Seat> findSeatById(@Param("seatId") String seatId);
}
