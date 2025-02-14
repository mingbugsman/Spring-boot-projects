package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Seat;

import java.util.List;

public interface ISeatRepository {
    List<SeatResponse> getAllSeats();

    SeatResponse getSeat(String seatId);

    SeatResponse createNewSeat(SeatCreationRequest request);

    SeatResponse updateSeat(String seatId, SeatUpdateRequest request);

    void deleteSeat(String seatId);

    Seat findSeatById(String seatId);
}
