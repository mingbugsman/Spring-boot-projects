package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomTicketMapper;
import com.TicketSelling.TicketSelling.Mapper.TicketMapper;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketService {
    ITicketRepository ticketRepository;
    TicketMapper ticketMapper;
    CustomTicketMapper customTicketMapper;


    List<TicketResponse> getAllTickets() {
        return ticketRepository
                .getAllTickets()
                .stream().map(ticketMapper::toTicketResponse)
                .toList();
    }
    List<TicketResponse> getAllTicketsByCustomerId(String customerId) {
        return ticketRepository.getAllTicketsByCustomerId(customerId)
                .stream().map(ticketMapper::toTicketResponse).toList();
    }

    TicketDetailResponse getDetailTicket(TicketPK ticketPK) {
        return  customTicketMapper.toTicketDetailResponse(
                        ticketRepository.findTicketById(ticketPK)
                );
    }
    /*
    Ticket createNewTicket(TicketCreationRequest request) {
        TicketPK ticketPK = createTicketPK(request.getConcertId(), request.getSeatId());
        if (ticketRepository.findTicketById(ticketPK) != null) {
            throw new ApplicationException(ErrorCode.TICKET_EXISTED);
        }
        Ticket ticket = ticketMapper.toTicket(request);
        ticket = ticketRepository.save(ticket);
        return ticket;
    }*/

    TicketResponse updateTicket(String concertId, String seatId, TicketUpdateRequest request) {
        TicketPK ticketPK = TicketPK.builder()
                .concertId(concertId)
                .seatId(seatId)
                .build();

        Ticket ticket = ticketRepository.findTicketById(ticketPK);
        ticketMapper.updateTicket(ticket, request);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    void deleteTicket(TicketPK ticketPK) {
        Ticket ticket = ticketRepository.findTicketById(ticketPK);
        ticketRepository.deleteTicket(ticket);
    }
    private TicketPK createTicketPK(String concertId, String seatId) {
        return TicketPK.builder().seatId(seatId).concertId(concertId).build();
    }
}
