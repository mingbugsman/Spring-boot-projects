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
@RequestMapping("/api/seats")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatController {

    SeatService seatService;

    @GetMapping
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

    @PostMapping("/{seatCategoryId}")
    public ApiResponse<SeatResponse> createNewSeat(
            @PathVariable String seatCategoryId,
            @RequestParam int seatNumber) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.createNewSeat(seatCategoryId ,seatNumber))
                .build();
    }

    @PostMapping("/list/by-hall/{hallId}/seat-category/{seatCategoryId}")
    public ApiResponse<List<SeatResponse>> createListSeat(
            @PathVariable String seatCategoryId,
            @PathVariable String hallId,
            @RequestBody int totalSeats) {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.createListSeat(hallId, seatCategoryId ,totalSeats))
                .build();
    }

    @PutMapping("/{seatId}")
    public ApiResponse<SeatResponse> updateSeat(
            @PathVariable String seatId,
            @Valid @RequestBody SeatUpdateRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.updateSeat(seatId, request))
                .build();
    }

    @DeleteMapping("/{seatId}")
    public ApiResponse<String> deleteSeat(@PathVariable String seatId) {
        seatService.deleteSeat(seatId);
        return ApiResponse.<String>builder()
                .message("Seat with ID " + seatId + " is successfully deleted")
                .build();
    }
}
