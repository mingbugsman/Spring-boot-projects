package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomCustomerMapper;
import com.TicketSelling.TicketSelling.Mapper.HallMapper;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HallService {
    IHallRepository hallRepository;
    HallMapper hallMapper;

    public HallDetailResponse getHallDetail(String hallId) {
        Hall hall = hallRepository.findHallById(hallId);
        return hallMapper.toHallDetailResponse(hall);
    }

    public List<HallResponse> getAllHalls() {
        return hallRepository.getAllHalls()
                .stream()
                .map(hallMapper::toHallResponse).toList();
    }

    public HallResponse createNewHall(HallCreationRequest request) {
        if (hallRepository.existsByAddress(request.getAddress())) {
            throw new ApplicationException(ErrorCode.ADDRESS_EXISTED);
        }
        Hall createdhall = hallMapper.toHall(request);
        createdhall = hallRepository.save(createdhall);
        return hallMapper.toHallResponse(createdhall);
    }

    public HallResponse updateHall(String hallId, HallUpdateRequest request) {
        Hall foundHall = hallRepository.findHallById(hallId);

        hallMapper.updateHall(foundHall, request);
        foundHall = hallRepository.save(foundHall);
        return hallMapper.toHallResponse(foundHall);
    }

    public void deleteHall(String hallId) {
        var hall = hallRepository.findHallById(hallId);
        hallRepository.deleteHall(hall);
    }
}
