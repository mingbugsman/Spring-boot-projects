package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPriceRepository extends JpaRepository<TicketPrice, String> {
}
