package com.TicketSelling.TicketSelling.Mapper.CustomMapper;

import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Mapper.BandMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomConcertMapper {
    BandMapper bandMapper;
    public ConcertDetailResponse toConcertDetailResponse(Concert concert) {

        return new ConcertDetailResponse(
                concert.getId(),
                concert.getDate(),
                concert.getStartDate(),
                concert.getConcertName(),
                concert.getConcertStatus(),
                concert.getHall().getHallName(),
                concert.getConcertInformation(),
                concert.getUpdatedAt(),
                concert.getBands().stream().map(bandMapper::toBandResponse).toList()
        );
    }
}
