package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatCategoryJpaRepository extends JpaRepository<SeatCategory, String> {

    boolean existsByRowLabelAndHallId(String rowLabel, String hallId);

    @Query("SELECT s FROM Concert c " +
            "JOIN c.hall h " +
            "JOIN h.seatCategories s " +
            "WHERE c.id = :concertId AND c.deletedAt IS NULL AND h.deletedAt IS NULL AND s.deletedAt IS NULL")
    List<SeatCategory> getAllSeatCategoriesByConcertId(@Param("concertId") String concertId);


}

