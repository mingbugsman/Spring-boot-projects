package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.HallMapper;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.HallJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallRepositoryImp implements IHallRepository {
    HallJpaRepository hallJpaRepository;
    HallMapper hallMapper;

    @Override
    public HallDetailResponse getHallDetail(String hallId) {
        return hallMapper.toHallDetailResponse(findHallById(hallId));
    }

    @Override
    public List<HallResponse> getAllHalls() {
        return hallJpaRepository.findAll().stream().map(hallMapper::toHallResponse).toList();
    }

    @Override
    public HallResponse createNewHall(HallCreationRequest request) {
        if (hallJpaRepository.existsByAddress(request.getAddress())) {
            throw new ApplicationException(ErrorCode.ADDRESS_EXISTED);
        }
        Hall createdhall = hallMapper.toHall(request);
        createdhall = hallJpaRepository.save(createdhall);
        return hallMapper.toHallResponse(createdhall);
    }

    @Override
    public HallResponse updateHall(String hallId, HallUpdateRequest request) {
        Hall foundHall = findHallById(hallId);

        hallMapper.updateHall(foundHall, request);
        foundHall = hallJpaRepository.save(foundHall);
        return hallMapper.toHallResponse(foundHall);
    }

    @Override
    public void deleteHall(String hallId) {
        if (hallJpaRepository.existsById(hallId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        hallJpaRepository.deleteById(hallId);
    }

    @Override
    public Hall findHallById(String hallId) {
        return hallJpaRepository.findById(hallId).orElseThrow(
                () -> new ApplicationException(ErrorCode.NOT_FOUND_ID)
        );
    }
}
