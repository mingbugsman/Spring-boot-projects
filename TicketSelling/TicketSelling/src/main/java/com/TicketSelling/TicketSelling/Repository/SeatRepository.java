package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {
}
