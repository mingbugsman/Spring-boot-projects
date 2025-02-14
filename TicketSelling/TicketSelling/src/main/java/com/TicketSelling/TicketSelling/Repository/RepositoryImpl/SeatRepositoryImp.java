package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

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
import com.TicketSelling.TicketSelling.Repository.Jpa.SeatJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SeatRepositoryImp implements ISeatRepository {
    IHallRepository hallRepository;
    SeatJpaRepository seatJpaRepository;
    SeatMapper seatMapper;

    @Override
    public List<SeatResponse> getAllSeats() {
        return seatJpaRepository.findAll().stream().map(seatMapper::toSeatResponse).collect(Collectors.toList());
    }

    @Override
    public SeatResponse getSeat(String seatId) {
        return seatMapper.toSeatResponse(findSeatById(seatId));
    }

    @Override
    public SeatResponse createNewSeat(SeatCreationRequest request) {
        if (seatJpaRepository.findByRowNumberAndSeatNumberAndHallId(
                request.getSeatNumber(),
                request.getSeatNumber(),
                request.getHallId()
        ) != null) {
            throw new ApplicationException(ErrorCode.SEAT_EXISTED);
        }
        Seat seat = seatMapper.toSeat(request);
        seat = seatJpaRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public SeatResponse updateSeat(String seatId, SeatUpdateRequest request) {
        Seat seat = findSeatById(seatId);
        seatMapper.updateSeat(seat,request);
        seat.setHall(hallRepository.findHallById(request.getHallId()));
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public void deleteSeat(String seatId) {
        if (!seatJpaRepository.existsById(seatId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        seatJpaRepository.deleteById(seatId);
    }

    @Override
    public Seat findSeatById(String seatId) {
        return seatJpaRepository.findById(seatId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }
}
