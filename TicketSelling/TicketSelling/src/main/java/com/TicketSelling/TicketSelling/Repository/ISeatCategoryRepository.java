package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.SeatCategory;

import java.util.List;

public interface ISeatCategoryRepository {
    List<SeatCategory> getAllSeatCategories();
    List<SeatCategory> getAllSeatsByConcertId(String concertId);

    SeatCategory save(SeatCategory seatCategory);

    void deleteSeatCategory(SeatCategory seatCategory);

    SeatCategory findSeatCategoryById(String seatCategoryId);

    boolean existsByRowLabelAndHallId(String rowLabel, String hallId);


    List<SeatCategory> getAllSeatCategoriesByConcertId(String concertId) ;
}
