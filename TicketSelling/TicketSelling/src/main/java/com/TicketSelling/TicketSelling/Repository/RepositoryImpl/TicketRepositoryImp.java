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

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TicketRepositoryImp implements ITicketRepository {
    TicketJpaRepository ticketJpaRepository;

    @Override
    public List<TicketResponse> getAllTickets() {
        return List.of();
    }

    @Override
    public TicketDetailResponse getDetailTicket() {
        return null;
    }

    @Override
    public TicketResponse createNewTicket(Ticket ticket) {
        return null;
    }

    @Override
    public TicketResponse updateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public void deleteTicket(TicketPK ticketPK) {

    }

    @Override
    public Ticket findTicketById(TicketPK ticketPK) {
        return ticketJpaRepository.findByTickPK(ticketPK.getConcertId(), ticketPK.getConcertId()).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }
}
