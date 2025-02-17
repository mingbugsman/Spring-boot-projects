package com.TicketSelling.TicketSelling.Controller;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Service.SeatService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatController {

    SeatService seatService;

    public ApiResponse<List<SeatResponse>> getAllSeats() {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.getAllSeats())
                .build();
    }

    @GetMapping("/{seatId}")
    public ApiResponse<SeatResponse> getSeat(@PathVariable String seatId) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.getSeat(seatId))
                .build();
    }

    @PostMapping("/{hallId}")
    public ApiResponse<SeatResponse> createNewSeat(@Valid @RequestBody  SeatCreationRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.createNewSeat(request))
                .build();
    }

    @PostMapping("/list/{hallId}")
    public ApiResponse<List<SeatResponse>> createListSeat(@PathVariable String hallId, @RequestBody  List<SeatCreationRequest> request) {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.createListSeat(hallId,request))
                .build();
    }

    @PutMapping("/{seatId}")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable String seatId,@RequestBody SeatUpdateRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.updateSeat(seatId,request))
                .build();
    }

    @DeleteMapping("/{seatId}")
    public ApiResponse<String> deleteSeat(@PathVariable String seatId) {
        seatService.deleteSeat(seatId);
        return ApiResponse.<String>builder()
                .result(seatId + " is successfully deleted")
                .build();
    }
}
