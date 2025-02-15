package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketJpaRepository extends JpaRepository<Ticket, String> {

    @Query("SELECT t FROM Ticket t " +
            "WHERE t.id.concertId = :concertId AND t.id.seatId = :seatId")
    Optional<Ticket> findByTickPK(@Param("concertId") String concertId,
                                  @Param("seatId") String seatId);

    @Query("SELECT t From Ticket t " +
            "JOIN t.booking b " +
            "JOIN b.customer c " +
            "WHERE c.id = :customerId")
    List<Ticket> findAllTicketsByCustomerId(@Param("customerId") String customerId);

}
