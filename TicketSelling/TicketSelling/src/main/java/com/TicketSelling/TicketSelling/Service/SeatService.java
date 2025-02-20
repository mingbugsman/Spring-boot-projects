package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.SeatMapper;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.ISeatCategoryRepository;
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
    ISeatCategoryRepository seatCategoryRepository;
    SeatMapper seatMapper;


    public List<SeatResponse> getAllSeats() {
        return seatRepository.getAllSeats().stream().map(seatMapper::toSeatResponse).toList();
    }

    public List<SeatResponse> getAllSeatsBySeatCategoryId(String seatCategoryId) {
        return seatRepository.getAllSeatsBySeatCategoryId(seatCategoryId)
                .stream()
                .map(seatMapper::toSeatResponse)
                .toList();
    }
    public SeatResponse getSeat(String seatId) {
        return seatMapper.toSeatResponse(seatRepository.findSeatById(seatId));
    }



    public SeatResponse createNewSeat(String seatCategoryId, int seatNumber) {
        System.out.println("Seat Information : "  + seatNumber + " + " + seatCategoryId);

        if (seatRepository.existsBySeatNumberAndSeatCategoryId(
                seatNumber,
                seatCategoryId
        )) {
            throw new ApplicationException(ErrorCode.SEAT_EXISTED);
        }

        Seat seat = Seat.builder()
                .seatNumber(seatNumber)
                .build();

        SeatCategory seatCategory = seatCategoryRepository.findSeatCategoryById(seatCategoryId);
        seat.setSeatCategory(seatCategory);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    public List<SeatResponse> createListSeat(String hallId, String seatCategoryId, int totalSeats) {
        System.out.println(seatCategoryId);
        System.out.println(hallId);
        System.out.println(totalSeats);
        Hall hall = hallRepository.findHallById(hallId);
        List<SeatResponse> seatResponses = new ArrayList<>();
        for (int seatNumber = 1; seatNumber <= totalSeats; seatNumber++) {
            seatResponses.add(createNewSeat(seatCategoryId, seatNumber ));
        }
        return seatResponses;
    }

    public SeatResponse updateSeat(String seatId, SeatUpdateRequest request) {
        Seat seat = seatRepository.findSeatById(seatId);
        seatMapper.updateSeat(seat,request);
        seat.setSeatCategory(seatCategoryRepository.findSeatCategoryById(request.getSeatCategoryId()));
        return seatMapper.toSeatResponse(seat);
    }

    public void deleteSeat(String seatId) {
        Seat seat = seatRepository.findSeatById(seatId);
        seatRepository.deleteSeat(seat);
    }
}
