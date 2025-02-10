package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, String> {
}
