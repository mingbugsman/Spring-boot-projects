package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;

import java.util.List;


public interface ITicketRepository {
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsByCustomerId(String customerId);
    Ticket save(Ticket ticket);
    void deleteTicket(Ticket ticket);
    Ticket findTicketById(TicketPK ticketPK);
    void saveAll(List<Ticket> tickets );
}
