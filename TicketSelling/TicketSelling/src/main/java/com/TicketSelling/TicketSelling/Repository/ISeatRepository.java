package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Seat;

import java.util.List;

public interface ISeatRepository {
    List<Seat> getAllSeats();

    Seat save(Seat seat);

    void deleteSeat(Seat seat);

    Seat findSeatById(String seatId);

    boolean existsByRowNumberAndSeatNumberAndHallId(Integer rowNumber, Integer seatNumber, String hallId);


    List<Seat> getAllSeatsByConcertId(String concertId) ;


}
