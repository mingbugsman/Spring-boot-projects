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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SeatRepositoryImp implements ISeatRepository {
    SeatJpaRepository seatJpaRepository;

    @Override
    public List<Seat> getAllSeats() {
        return seatJpaRepository.getAllSeats();
    }

    @Override
    public Seat save(Seat seat) {
        return seatJpaRepository.save(seat);
    }


    @Override
    public void deleteSeat(Seat seat) {
        seat.setDeletedAt(LocalDateTime.now());
        seatJpaRepository.save(seat);
    }

    @Override
    public Seat findSeatById(String seatId) {
        return seatJpaRepository.findById(seatId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public boolean existsByRowNumberAndSeatNumberAndHallId(Integer rowNumber, Integer seatNumber, String hallId) {
        return seatJpaRepository.existsByRowNumberAndSeatNumberAndHallId(rowNumber, seatNumber, hallId);
    }

    @Override
    public List<Seat> getAllSeatsByConcertId(String concertId) {
        return seatJpaRepository.getAllSeatsByConcertId(concertId);
    }
}
