package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallService {
    IHallRepository hallRepository;

    HallDetailResponse getHallDetail(String hallId) {
        return hallRepository.getHallDetail(hallId);
    }

    List<HallResponse> getAllHalls() {
        return hallRepository.getAllHalls();
    }

    HallResponse createNewHall(HallCreationRequest request) {
        return hallRepository.createNewHall(request);
    }

    HallResponse updateHall(String hallId, HallUpdateRequest request) {
        return hallRepository.updateHall(hallId, request);
    }

    void deleteHall(String hallId) {
        hallRepository.deleteHall(hallId);
    }
}
