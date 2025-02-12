package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Enum.HallStatus;
import com.TicketSelling.TicketSelling.Utils.CastingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
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

    default HallDetailResponse toHallDetailResponse(Object[] data) {
        if(data == null || data.length < 7) {
            return null;
        }
        return new HallDetailResponse(
                (String )data[0],
                (String) data[1],
                (HallStatus) data[2],
                (String) data[3],
                (Integer) data[4],
                (Integer) data[5],
                CastingUtil.safeCastList(data[6], ConcertResponse.class)
        );
    }


}
