package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, String> {
    Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate);

    @Query("SELECT c FROM Concert c " +
            "WHERE c.deletedAt IS NULL")
    List<Concert> getAllConcerts();

}
