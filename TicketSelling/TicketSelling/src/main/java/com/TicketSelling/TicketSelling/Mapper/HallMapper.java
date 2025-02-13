package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ConcertMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HallMapper {

    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hallStatus", ignore = true)
    Hall toHall(HallCreationRequest request);



    HallResponse toHallResponse(Hall hall);

    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateHall(@MappingTarget Hall hall, HallUpdateRequest request);



    @Mapping(target = "concerts", source = "concerts")
    @Mapping(target = "totalSeats", expression = "java(getTotalSeats(hall))")
    @Mapping(target = "totalConcerts", expression = "java(getTotalConcerts(hall))")
    HallDetailResponse toHallDetailResponse(Hall hall);

    default int getTotalSeats(Hall hall) {
        return (hall.getSeats() != null) ? hall.getSeats().size() : 0;
    }

    default int getTotalConcerts(Hall hall) {
        return (hall.getConcerts() != null) ? hall.getConcerts().size() : 0;
    }


}
