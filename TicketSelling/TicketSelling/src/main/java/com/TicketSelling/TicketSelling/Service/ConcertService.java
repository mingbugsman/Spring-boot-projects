package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateBands;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.ConcertMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomConcertMapper;
import com.TicketSelling.TicketSelling.Repository.IBandRepository;
import com.TicketSelling.TicketSelling.Repository.IConcertRepository;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Utils.SortUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertService  {
    IConcertRepository concertRepository;
    IBandRepository bandRepository;
    IHallRepository hallRepository;
    ConcertMapper concertMapper;
    CustomConcertMapper customConcertMapper;

    public ConcertDetailResponse getConcertDetail(String concertId) {
        Concert concert = concertRepository.findConcertById(concertId);
        return customConcertMapper.toConcertDetailResponse(concert);
    }

    public List<ConcertResponse> getAllConcerts() {
        var sortedConcerts = SortUtils.sortList(concertRepository.getAllConcerts(),
                SortOrder.DESC, Concert::getStartDate);
        return sortedConcerts.stream().map(concertMapper::toConcertResponse).collect(Collectors.toList());
    }

    public ConcertResponse createNewConcert(ConcertCreationRequest request) {

        if (concertRepository.findByConcertNameAndStartDate(request.getConcertName(),request.getStartDate()) != null) {
            throw new ApplicationException(ErrorCode.CONCERT_EXISTED);
        }
        Hall hall = hallRepository.findHallById(request.getHallId());
        Concert concert = concertMapper.toConcert(request);
        concert.setHall(hall);
        concert = concertRepository.save(concert);

        return concertMapper.toConcertResponse(concert);
    }

    public List<ConcertResponse> createNewListConcert( List<ConcertCreationRequest> requests) {
        List<ConcertResponse> concertResponses = new ArrayList<>();
        for (var req : requests) {
            var res = createNewConcert(req);
            concertResponses.add(res);
        }
        return concertResponses;
    }

    public ConcertResponse updateConcert(String concertId, ConcertUpdateRequest request) {
        Concert concert = concertRepository.findConcertById(concertId);

        if (request.getHallId() != null) {
            Hall hall = hallRepository.findHallById(request.getHallId());
            concert.setHall(hall);
        }
        concertMapper.updateConcert(concert, request);
        if (request.getBandIds() != null) {
            Set<Band> bands = request.getBandIds().stream()
                    .map(bandRepository::findBandById)
                    .collect(Collectors.toSet());
            concert.setBands(bands);
        }
        concertRepository.save(concert);

        return concertMapper.toConcertResponse(concert);
    }
    public List<ConcertResponse> updateBandForConcert(List<ConcertUpdateBands> requests) {
        List<ConcertResponse> concertResponses = new ArrayList<>();
        for (var req : requests) {
            Concert concert = concertRepository.findConcertById(req.getConcertId());
            if (req.getBandIds() != null) {
                Set<Band> bands = req.getBandIds().stream()
                        .map(bandRepository::findBandById)
                        .collect(Collectors.toSet());
                concert.setBands(bands);

            }
            concertRepository.save(concert);
            concertResponses.add(concertMapper.toConcertResponse(concert));
        }
        return concertResponses;
    }

    public void deleteConcert(String concertId) {
        Concert concert = concertRepository.findConcertById(concertId);
        concertRepository.deleteConcert(concert);
    }
}
