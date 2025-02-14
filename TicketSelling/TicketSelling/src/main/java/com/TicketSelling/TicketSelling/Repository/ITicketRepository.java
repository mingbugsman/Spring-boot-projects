package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ITicketRepository {
    List<TicketResponse> getAllTickets();
    TicketDetailResponse getDetailTicket();
    TicketResponse createNewTicket(Ticket ticket);
    TicketResponse updateTicket(Ticket ticket);
    void deleteTicket(TicketPK ticketPK);
    Ticket findTicketById(TicketPK ticketPK);
}
