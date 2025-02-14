package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat,String> {
    Seat findByRowNumberAndSeatNumberAndHallId(Integer rowNumber, Integer seatNumber, String hallId);
}
