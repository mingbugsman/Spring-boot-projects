package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Entity.Concert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConcertMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "bands", ignore = true)
    @Mapping(target = "concertStatus", ignore = true)
    Concert toConcert(ConcertCreationRequest request);

    @Mapping(target = "hallName", source = "hall.hallName")
    @Mapping(target = "hallId", source = "hall.id")
    @Mapping(target = "totalBands", expression = "java(getTotalBands(concert))")
    @Mapping(target = "lastUpdate", source = "updatedAt")
    @Mapping(target = "createdDate",source = "date")
    ConcertResponse toConcertResponse(Concert concert);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "bands", ignore = true)

    void updateConcert(@MappingTarget Concert concert, ConcertUpdateRequest request);


    @Mapping(target = "bandDetailList", ignore = true)
    @Mapping(target = "hallName", source = "hall.hallName")
    ConcertDetailResponse toConcertDetailResponse(Concert concert);

    default int getTotalBands(Concert concert) {
        return (concert.getBands() != null) ? concert.getBands().size() : 0;
    }
}
