package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, String> {
}
