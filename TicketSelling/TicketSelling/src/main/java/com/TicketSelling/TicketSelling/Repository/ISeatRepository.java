package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Seat;

import java.util.List;

public interface ISeatRepository {
    List<Seat> getAllSeats();

    Seat save(Seat seat);

    void deleteSeat(Seat seat);

    Seat findSeatById(String seatId);

    boolean existsBySeatNumberAndSeatCategoryId(Integer seatNumber, String seatCategoryId);


    List<Seat> getAllSeatsBySeatCategoryId(String seatCategoryId) ;


}
