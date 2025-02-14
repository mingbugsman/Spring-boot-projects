package com.TicketSelling.TicketSelling.Service;



import com.TicketSelling.TicketSelling.DTO.Request.Band.BandCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BandMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomBandMapper;
import com.TicketSelling.TicketSelling.Repository.IBandRepository;
import com.TicketSelling.TicketSelling.Repository.IConcertRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandService {
    IBandRepository bandRepository;
    IConcertRepository concertRepository;
    BandMapper bandMapper;
    CustomBandMapper customBandMapper;
    //GET
    public BandDetailResponse getBandDetail(String bandId) {

        return customBandMapper.toBandDetailResponse(bandRepository.findBandById(bandId));
    }
    List<BandResponse> getAllBands() {
        return bandRepository.getAllBands().stream().map(bandMapper::toBandResponse).collect(Collectors.toList());
    }

    // POST
    BandResponse createNewBand(BandCreationRequest request) {
        if (bandRepository.findByBandNameAndCountryAndFoundingYear(request.getBandName(), request.getCountry(), request.getFoundingYear()) != null) {
            throw new ApplicationException(ErrorCode.BAND_EXISTED);
        }
        Band band = bandMapper.toBand(request);
        band = bandRepository.save(band);
        return bandMapper.toBandResponse(band);
    }

    // PUT/PATCH
    BandResponse updateBand(String bandId, BandUpdateRequest request) {
        Band band = bandRepository.findBandById(bandId);


        bandMapper.updateBand(band, request);

        Set<Concert> concerts = request.getConcertIds().stream()
                .map(concertRepository::findConcertById)
                .collect(Collectors.toSet());

        band.setConcerts(concerts);
        bandRepository.save(band);

        return bandMapper.toBandResponse(band);
    }

    // DELETE
    void deleteBand(String bandId) {
        bandRepository.deleteBand(bandId);
    }


}
