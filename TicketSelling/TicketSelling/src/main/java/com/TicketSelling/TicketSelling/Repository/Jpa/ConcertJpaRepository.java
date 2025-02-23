package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, String> {
    Concert findByConcertNameAndStartDate(String concertName, LocalDateTime startDate);

    @Query("SELECT c FROM Concert c " +
            "WHERE c.deletedAt IS NULL")
    List<Concert> getAllConcerts();

    @Query("SELECT co from Concert co " +
            "WHERE co.deletedAt IS NULL AND co.id = :concertId")
    Optional<Concert> findConcertById(@Param("concertId") String concertId);

    @Query(value = "SELECT * FROM concerts WHERE LOWER(concert_name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND concert_status = :concertStatus", nativeQuery = true)
    List<Concert> searchByKeyWord(
            @Param("keyword") String keyword,
            @Param("concertStatus") String concertStatus
    );

}
