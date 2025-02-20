package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Repository.ISeatCategoryRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.SeatCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor

public class SeatCategoryRepositoryImp implements ISeatCategoryRepository {

    private final SeatCategoryJpaRepository seatCategoryJpaRepository;

    @Override
    public List<SeatCategory> getAllSeatCategories() {
        return seatCategoryJpaRepository.findAll();
    }

    @Override
    public List<SeatCategory> getAllSeatsByConcertId(String concertId) {
        return seatCategoryJpaRepository.getAllSeatCategoriesByConcertId(concertId);
    }

    @Override
    public List<Seat> getStatusSeats(String seatCategoryId, SeatStatus seatStatus) {
        return seatCategoryJpaRepository.getStatusSeats(seatCategoryId, seatStatus);
    }

    @Override
    public SeatCategory save(SeatCategory seatCategory) {
        return seatCategoryJpaRepository.save(seatCategory);
    }

    @Override
    public void deleteSeatCategory(SeatCategory seatCategory) {
        seatCategory.setDeletedAt(LocalDateTime.now());
    }

    @Override
    public SeatCategory findSeatCategoryById(String seatCategoryId) {
        return seatCategoryJpaRepository.findById(seatCategoryId).orElseThrow(
                () -> new ApplicationException(ErrorCode.NOT_FOUND_ID)
        );
    }

    @Override
    public boolean existsByRowLabelAndHallId(String rowLabel, String hallId) {
        return seatCategoryJpaRepository.existsByRowLabelAndHallId(rowLabel, hallId);
    }

    @Override
    public boolean isSeatAvailable(String rowLabel, Integer seatNumber, String seatCategoryId) {
        return seatCategoryJpaRepository.isSeatAvailable(rowLabel, seatNumber, seatCategoryId);
    }

    @Override
    public List<SeatCategory> getAllSeatCategoriesByConcertId(String concertId) {

        return seatCategoryJpaRepository.getAllSeatCategoriesByConcertId(concertId);
    }
}
