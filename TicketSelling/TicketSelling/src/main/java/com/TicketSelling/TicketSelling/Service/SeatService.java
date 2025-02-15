package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.SeatMapper;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.ISeatRepository;

import java.util.List;

public class SeatService {
    ISeatRepository seatRepository;
    IHallRepository hallRepository;
    SeatMapper seatMapper;


    List<SeatResponse> getAllSeats() {
        return seatRepository.getAllSeats().stream().map(seatMapper::toSeatResponse).toList();
    }

    List<SeatResponse> getAllSeatsByConcertId(String concertId) {
        return seatRepository.getAllSeatsByConcertId(concertId)
                .stream()
                .map(seatMapper::toSeatResponse)
                .toList();
    }
    SeatResponse getSeat(String seatId) {
        return seatMapper.toSeatResponse(seatRepository.findSeatById(seatId));
    }

    public SeatResponse createNewSeat(SeatCreationRequest request) {

        if (seatRepository.existsByRowNumberAndSeatNumberAndHallId(
                request.getRowNumber(),
                request.getSeatNumber(),
                request.getHallId()
        )) {
            throw new ApplicationException(ErrorCode.SEAT_EXISTED);
        }
        Seat seat = seatMapper.toSeat(request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }


    public SeatResponse updateSeat(String seatId, SeatUpdateRequest request) {
        Seat seat = seatRepository.findSeatById(seatId);
        seatMapper.updateSeat(seat,request);
        seat.setHall(hallRepository.findHallById(request.getHallId()));
        return seatMapper.toSeatResponse(seat);
    }

    public void deleteSeat(String seatId) {
        Seat seat = seatRepository.findSeatById(seatId);
        seatRepository.deleteSeat(seat);
    }
}
