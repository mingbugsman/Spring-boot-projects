package com.TicketSelling.TicketSelling.Repository;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;

import java.time.LocalDateTime;
import java.util.List;

public interface IConcertRepository {
    List<Concert> getAllConcerts();
    Concert save( Concert concert);
    void deleteConcertById(String concertId);
    Concert findConcertById(String concertId);
    Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate);
    boolean existsById(String concertId);
}
