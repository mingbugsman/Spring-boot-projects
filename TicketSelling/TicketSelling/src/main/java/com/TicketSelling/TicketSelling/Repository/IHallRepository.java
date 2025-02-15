package com.TicketSelling.TicketSelling.Repository;


import com.TicketSelling.TicketSelling.Entity.Hall;

import java.util.List;

public interface IHallRepository {

    List<Hall> getAllHalls();
    Hall save(Hall hall);
    void deleteHall(Hall hall);
    Hall findHallById(String hallId);
    boolean existsByAddress(String address);
}
