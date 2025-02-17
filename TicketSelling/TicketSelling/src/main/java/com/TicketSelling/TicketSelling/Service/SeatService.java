package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.SeatMapper;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.ISeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatService {
    ISeatRepository seatRepository;
    IHallRepository hallRepository;
    SeatMapper seatMapper;


    public List<SeatResponse> getAllSeats() {
        return seatRepository.getAllSeats().stream().map(seatMapper::toSeatResponse).toList();
    }

    public List<SeatResponse> getAllSeatsByConcertId(String concertId) {
        return seatRepository.getAllSeatsByConcertId(concertId)
                .stream()
                .map(seatMapper::toSeatResponse)
                .toList();
    }
    public SeatResponse getSeat(String seatId) {
        return seatMapper.toSeatResponse(seatRepository.findSeatById(seatId));
    }



    public SeatResponse createNewSeat(SeatCreationRequest request) {
        System.out.println("Seat Information : " + request.getRow() + " - " + request.getSeatNumber() + " - " + request.getHallId() + " + " + request.getSeatType().name() + " + " + request.getPrice());

        if (seatRepository.existsByRowAndSeatNumberAndHallId(
                request.getRow(),
                request.getSeatNumber(),
                request.getHallId()
        )) {
            throw new ApplicationException(ErrorCode.SEAT_EXISTED);
        }

        Seat seat = seatMapper.toSeat(request);

        Hall hall = hallRepository.findHallById(request.getHallId());
        seat.setHall(hall);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    public List<SeatResponse> createListSeat(String hallId, List<SeatCreationRequest> requests) {
        Hall hall = hallRepository.findHallById(hallId);
        List<SeatResponse> seatResponses = new ArrayList<>();
        for (var request : requests) {
            request.setHallId(hall.getId());;

            seatResponses.add(createNewSeat(request));
        }
        return seatResponses;
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
