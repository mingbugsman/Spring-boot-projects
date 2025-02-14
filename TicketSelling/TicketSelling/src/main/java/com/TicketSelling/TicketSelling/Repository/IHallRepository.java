package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;

import java.util.List;

public interface IHallRepository {

    // GET
    HallDetailResponse getHallDetail(String hallId);
    List<HallResponse> getAllHalls();

    // POST
    HallResponse createNewHall(HallCreationRequest request);

    // PUT/PATCH
    HallResponse updateHall(String hallId, HallUpdateRequest request);

    // DELETE
    void deleteHall(String hallId);

    // Helper
    Hall findHallById(String hallId);
}
