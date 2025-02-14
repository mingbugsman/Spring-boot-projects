package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.ConcertMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomConcertMapper;
import com.TicketSelling.TicketSelling.Repository.IBandRepository;
import com.TicketSelling.TicketSelling.Repository.IConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService  {
    private final IConcertRepository concertRepository;
    private final IBandRepository bandRepository;
    private final ConcertMapper concertMapper;
    CustomConcertMapper customConcertMapper;

    public ConcertDetailResponse getConcertDetail(String concertId) {
        Concert concert = concertRepository.findConcertById(concertId);
        return customConcertMapper.toConcertDetailResponse(concert);
    }

    public List<ConcertResponse> getAllConcerts() {
        return concertRepository.getAllConcerts().stream().map(concertMapper::toConcertResponse).collect(Collectors.toList());
    }

    public ConcertResponse createNewConcert(ConcertCreationRequest request) {
        if (concertRepository.findByConcertNameAndStartDate(request.getConcertName(),request.getStartDate()) != null) {
            throw new ApplicationException(ErrorCode.CONCERT_EXISTED);
        }
        Concert concert = concertMapper.toConcert(request);
        concert = concertRepository.save(concert);
        return concertMapper.toConcertResponse(concert);
    }

    public ConcertResponse updateConcert(String concertId, ConcertUpdateRequest request) {
        Concert concert = concertRepository.findConcertById(concertId);

        concertMapper.updateConcert(concert, request);
        Set<Band> bands = request.getBandIds().stream()
                .map(bandRepository::findBandById)
                .collect(Collectors.toSet());

        concert.setBands(bands);
        concertRepository.save(concert);

        return concertMapper.toConcertResponse(concert);
    }
    public void deleteConcert(String concertId) {
        if (concertRepository.existsById(concertId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        concertRepository.deleteConcertById(concertId);
    }
}
