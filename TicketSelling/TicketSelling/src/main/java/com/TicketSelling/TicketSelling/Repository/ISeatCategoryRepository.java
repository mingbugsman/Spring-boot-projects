package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Seat;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import java.util.List;

public interface ISeatCategoryRepository {
    List<SeatCategory> getAllSeatCategories();
    List<SeatCategory> getAllSeatsByConcertId(String concertId);
    List<Seat> getStatusSeats(String seatCategoryId, SeatStatus seatStatus);
    SeatCategory save(SeatCategory seatCategory);

    void deleteSeatCategory(SeatCategory seatCategory);

    SeatCategory findSeatCategoryById(String seatCategoryId);

    boolean existsByRowLabelAndHallId(String rowLabel, String hallId);

    boolean isSeatAvailable( String rowLabel,
                             Integer seatNumber,
                             String seatCategoryId );

    List<SeatCategory> getAllSeatCategoriesByConcertId(String concertId) ;
}
