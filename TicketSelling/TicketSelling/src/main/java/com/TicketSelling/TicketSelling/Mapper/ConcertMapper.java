package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import com.TicketSelling.TicketSelling.Utils.CastingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConcertMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "bands", ignore = true)
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


    default ConcertDetailResponse toConcertDetailResponse(Object[] data) {
        if (data == null || data.length < 8) {
            return null;
        };
        return new ConcertDetailResponse(
                (String) data[0],
                (LocalDateTime) data[1],
                (LocalDateTime) data[2],
                (ConcertStatus) data[3],
                (String) data[4],
                (String) data[5],
                (LocalDateTime) data[6],
                CastingUtil.safeCastList(data[7], BandResponse.class)
        );
    }
    default int getTotalBands(Concert concert) {
        return (concert.getBands() != null) ? concert.getBands().size() : 0;
    }
}
