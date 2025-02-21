package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
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



    public TicketDetailResponse getDetailTicket(String concertId, String seatId) {
        System.out.println(concertId);
        System.out.println(seatId);
        TicketPK ticketPK = createTicketPK(concertId, seatId);
        return  customTicketMapper.toTicketDetailResponse(
                        ticketRepository.findTicketById(ticketPK)
        );
    }


    public TicketResponse updateTicket(String concertId, String seatId, TicketUpdateRequest request) {
        TicketPK ticketPK = createTicketPK(concertId,seatId);

        Ticket ticket = ticketRepository.findTicketById(ticketPK);
        ticketMapper.updateTicket(ticket, request);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    public void deleteTicket(String concertId, String seatId) {
        TicketPK ticketPK = createTicketPK(concertId, seatId);
        Ticket ticket = ticketRepository.findTicketById(ticketPK);
        ticketRepository.deleteTicket(ticket);
    }
    private TicketPK createTicketPK(String concertId, String seatId) {
        return TicketPK.builder().seatId(seatId).concertId(concertId).build();
    }
}
