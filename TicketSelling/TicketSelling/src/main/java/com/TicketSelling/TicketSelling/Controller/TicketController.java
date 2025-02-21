package com.TicketSelling.TicketSelling.Controller;

import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {


    private final TicketService ticketService;


    @GetMapping("/detail")
    public ApiResponse<TicketDetailResponse> getDetailTicket(
            @RequestParam String concertId,
            @RequestParam String seatId
    ) {
        return ApiResponse.<TicketDetailResponse>builder()
                .result(ticketService.getDetailTicket(concertId,seatId))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<TicketResponse> updateTicket(
            @RequestParam String concertId,
            @RequestParam String seatId,
            @RequestBody TicketUpdateRequest request
            ) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.updateTicket(concertId,seatId,request))
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteTicket(
            @RequestParam String concertId,
            @RequestParam String seatId
    ) {
        ticketService.deleteTicket(concertId,seatId);
        return ApiResponse.<String>builder()
                .result("Successfully delete ticket")
                .build();
    }

}
