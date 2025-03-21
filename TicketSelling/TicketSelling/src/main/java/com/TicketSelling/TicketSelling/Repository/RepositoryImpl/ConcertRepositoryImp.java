package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;


import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Repository.IConcertRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.ConcertJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertRepositoryImp implements IConcertRepository {

    ConcertJpaRepository concertJpaRepository;

    @Override
    public List<Concert> getAllConcerts() {
        return concertJpaRepository.getAllConcerts();
    }

    @Override
    public Concert save(Concert concert) {
        return concertJpaRepository.save(concert);

    }

    @Override
    public void deleteConcert(Concert concert) {
        concert.setDeletedAt(LocalDateTime.now());
        concertJpaRepository.save(concert);
    }

    @Override
    public Concert findConcertById(String concertId) {
        return concertJpaRepository.findConcertById(concertId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate) {
        return concertJpaRepository.findByConcertNameAndStartDate(concertName, startDate);
    }

    @Override
    public List<Concert> searchConcertByKeyWordAndConcertStatus(String KeyWord, ConcertStatus concertStatus) {
        return concertJpaRepository.searchByKeyWord(KeyWord, concertStatus.name());
    }

}
