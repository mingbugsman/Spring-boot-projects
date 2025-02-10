package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
