package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.TicketJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TicketRepositoryImp implements ITicketRepository {
    TicketJpaRepository ticketJpaRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketJpaRepository.getAllTickets();
    }

    @Override
    public List<Ticket> getAllTicketsByCustomerId(String customerId) {
        return ticketJpaRepository.findAllTicketsByCustomerId(customerId);
    }


    @Override
    public Ticket save(Ticket ticket) {
        return ticketJpaRepository.save(ticket);
    }



    @Override
    public void deleteTicket(Ticket ticket) {
        ticket.setDeletedAt(LocalDateTime.now());
        ticketJpaRepository.save(ticket);
    }

    @Override
    public Ticket findTicketById(TicketPK ticketPK) {
        return ticketJpaRepository.findByTickPK(ticketPK.getConcertId(), ticketPK.getSeatId()).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public void saveAll(List<Ticket> tickets) {
        ticketJpaRepository.saveAll(tickets);
    }
}
