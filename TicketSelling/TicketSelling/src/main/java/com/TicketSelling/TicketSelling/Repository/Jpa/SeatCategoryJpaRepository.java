package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
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


    @Query("SELECT sc FROM SeatCategory sc " +
            "JOIN sc.seats s " +
            "WHERE sc.id = :seatCategoryId AND sc.rowLabel = :rowLabel AND s.seatNumber = :seatNumber " +
            "AND sc.deletedAt IS NULL AND s.deletedAt IS NULL")
    boolean isSeatAvailable(@Param("rowLabel") String rowLabel,
                            @Param("seatNumber") Integer seatNumber,
                            @Param("seatCategoryId") String seatCategoryId );


    @Query(value = "SELECT s.* FROM seat s " +
            "JOIN seat_category sc ON s.seat_category_id = sc.id " +
            "WHERE sc.id = :seatCategoryId " +
            "AND sc.deleted_at IS NULL " +
            "AND s.deleted_at IS NULL " +
            "AND (:seatStatus IS NULL OR s.seat_status = :seatStatus)",
            nativeQuery = true)
    List<Seat> getStatusSeats(@Param("seatCategoryId") String seatCategoryId,
                              @Param("seatStatus") SeatStatus seatStatus);


}

