package com.TicketSelling.TicketSelling.Mapper.CustomMapper;

import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Mapper.ConcertMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomBandMapper {
    ConcertMapper concertMapper;

    public BandDetailResponse toBandDetailResponse(Band band) {
        return new BandDetailResponse(
                band.getId(),
                band.getBandName(),
                band.getBandStatus(),
                band.getBandInformation(),
                band.getUpdatedAt(),
                band.getConcerts().stream().map(concertMapper::toConcertResponse).toList()
        );
    }
}
