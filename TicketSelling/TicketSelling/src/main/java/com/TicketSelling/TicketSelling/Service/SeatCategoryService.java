package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.DTO.Response.SeatCategory.SeatCategoryResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.SeatCategoryMapper;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatCategoryService {
    ISeatCategoryRepository seatCategoryRepository;
    IHallRepository hallRepository;
    ISeatRepository seatRepository;
    SeatCategoryMapper seatCategoryMapper;
    private final SeatMapper seatMapper;

    public List<SeatResponse> getStatusSeats(String seatCategoryId, SeatStatus seatStatus) {
        return seatCategoryRepository.getStatusSeats(seatCategoryId, seatStatus).stream().map(seatMapper::toSeatResponse).collect(Collectors.toList());
    }

    public SeatCategoryResponse getSeatCategory(String seatCategoryId) {
        return seatCategoryMapper.toSeatCategoryResponse(seatCategoryRepository.findSeatCategoryById(seatCategoryId));
    }

    public List<SeatCategoryResponse> getAllSeatCategories() {
        return seatCategoryRepository.getAllSeatCategories().stream().map(seatCategoryMapper::toSeatCategoryResponse).collect(Collectors.toList());
    }

    public  List<SeatCategoryResponse> getAllSeatsByConcertId(String concertId) {
        return seatCategoryRepository.getAllSeatCategoriesByConcertId(concertId).stream().map(seatCategoryMapper::toSeatCategoryResponse).collect(Collectors.toList());
    }

    public SeatCategoryResponse createNewSeatCategory(String hallId, SeatCategoryCreationRequest request) {
        if  (seatCategoryRepository.existsByRowLabelAndHallId(request.getRowLabel(), hallId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        SeatCategory seatCategory = seatCategoryMapper.toSeatCategory(request);
        Hall hall = hallRepository.findHallById(hallId);
        seatCategory.setHall(hall);
        seatCategoryRepository.save(seatCategory);
        return seatCategoryMapper.toSeatCategoryResponse(seatCategory);
    }

    public List<SeatCategoryResponse> createNewListSeatCategory(String hallId, List<SeatCategoryCreationRequest> requests) {
        System.out.println(hallId);
        List<SeatCategoryResponse> seatCategoryResponses= new ArrayList<>();
         for (var req : requests) {
             var res = createNewSeatCategory(hallId, req);
             seatCategoryResponses.add(res);
         }
         return seatCategoryResponses;

    }

    public SeatCategoryResponse updateSeatCategory(String seatCategoryId, SeatCategoryUpdateRequest request) {
        SeatCategory seatCategory = seatCategoryRepository.findSeatCategoryById(seatCategoryId);
        seatCategoryMapper.updateSeatCategory(seatCategory, request);
        Hall hall = hallRepository.findHallById(request.getHallId());
        seatCategory.setHall(hall);
        if (request.getSeatIds() != null) {
            List<Seat> seats = new ArrayList<>();
            var seatIds = request.getSeatIds();
            for (var seatId : seatIds) {
                Seat seat = seatRepository.findSeatById(seatId);
                seats.add(seat);
            }
            seatCategory.setSeats(seats);
        }
        return seatCategoryMapper.toSeatCategoryResponse(seatCategoryRepository.save(seatCategory));

    }

    public void deleteSeatCategory(String seatCategoryId) {
        SeatCategory seatCategory = seatCategoryRepository.findSeatCategoryById(seatCategoryId);
        seatCategoryRepository.deleteSeatCategory(seatCategory);
    }
}
