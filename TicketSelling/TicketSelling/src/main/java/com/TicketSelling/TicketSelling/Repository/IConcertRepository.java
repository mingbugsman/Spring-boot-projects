package com.TicketSelling.TicketSelling.Repository;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IConcertRepository {
    List<Concert> getAllConcerts();
    Concert save( Concert concert);
    void deleteConcert(Concert concert);
    Concert findConcertById(String concertId);
    Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate);
    List<Concert> searchConcertByKeyWordAndConcertStatus(String KeyWord, ConcertStatus concertStatus);

}
