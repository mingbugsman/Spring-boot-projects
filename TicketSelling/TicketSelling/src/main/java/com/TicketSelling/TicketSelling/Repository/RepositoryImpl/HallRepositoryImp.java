package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;


import com.TicketSelling.TicketSelling.Entity.Hall;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Repository.IHallRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.HallJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HallRepositoryImp implements IHallRepository {
    HallJpaRepository hallJpaRepository;


    @Override
    public List<Hall> getAllHalls() {
        return hallJpaRepository.getAllHalls();
    }

    @Override
    public Hall save(Hall hall) {
        return hallJpaRepository.save(hall);
    }

    @Override
    public void deleteHall(Hall hall) {
        hall.setDeletedAt(LocalDateTime.now());
        hallJpaRepository.save(hall);
    }

    @Override
    public Hall findHallById(String hallId) {
        System.out.println(hallId);
        return hallJpaRepository.findHallById(hallId).orElseThrow(
                () -> new ApplicationException(ErrorCode.NOT_FOUND_ID)
        );
    }

    @Override
    public boolean existsByAddress(String address) {
        return hallJpaRepository.existsByAddress(address);
    }
}
