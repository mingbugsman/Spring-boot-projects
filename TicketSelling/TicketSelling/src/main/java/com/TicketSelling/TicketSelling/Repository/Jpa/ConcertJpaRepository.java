package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, String> {
    Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate);
}
