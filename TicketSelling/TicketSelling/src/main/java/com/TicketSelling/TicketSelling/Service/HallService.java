package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomCustomerMapper;
import com.TicketSelling.TicketSelling.Mapper.HallMapper;
import com.TicketSelling.TicketSelling.Repository.IConcertRepository;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.ISeatCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HallService {
    IHallRepository hallRepository;
    ISeatCategoryRepository seatCategoryRepository;
    IConcertRepository concertRepository;
    HallMapper hallMapper;

    public HallDetailResponse getHallDetail(String hallId) {
        Hall hall = hallRepository.findHallById(hallId);
        return hallMapper.toHallDetailResponse(hall);
    }

    public List<HallResponse> getAllHalls() {
        return hallRepository.getAllHalls()
                .stream()
                .map(hallMapper::toHallResponse).toList();
    }

    public HallResponse createNewHall(HallCreationRequest request) {
        if (hallRepository.existsByAddress(request.getAddress())) {
            throw new ApplicationException(ErrorCode.ADDRESS_EXISTED);
        }
        Hall createdhall = hallMapper.toHall(request);
        createdhall = hallRepository.save(createdhall);
        return hallMapper.toHallResponse(createdhall);
    }

    public List<HallResponse> createNewListHall(List<HallCreationRequest> requests) {
        List<HallResponse> hallResponses = new ArrayList<>();
        for (var req : requests) {
            var hallResponse = createNewHall(req);
            hallResponses.add(hallResponse);
        }
        return hallResponses;
    }


    public HallResponse updateHall(String hallId, HallUpdateRequest request) {
        Hall foundHall = hallRepository.findHallById(hallId);
        hallMapper.updateHall(foundHall, request);

        var concertIds = request.getConcertIds();
        var seatCategoryIds = request.getSeatCategoryIds();
        if (concertIds != null && seatCategoryIds != null) {

            List<Concert> concerts = new ArrayList<>();
            for (var concertId : concertIds) {
                concerts.add(concertRepository.findConcertById(concertId));
            }

            List<SeatCategory> seatCategories = new ArrayList<>();
            for (var seatCategoryId : seatCategoryIds) {
                seatCategories.add(seatCategoryRepository.findSeatCategoryById(seatCategoryId));
            }
            foundHall.setConcerts(concerts);
            foundHall.setSeatCategories(seatCategories);
        }

        foundHall = hallRepository.save(foundHall);
        return hallMapper.toHallResponse(foundHall);
    }

    public void deleteHall(String hallId) {
        var hall = hallRepository.findHallById(hallId);
        hallRepository.deleteHall(hall);
    }
}
