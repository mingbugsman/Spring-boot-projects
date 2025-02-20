package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.DTO.Response.SeatCategory.SeatCategoryResponse;
import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import com.TicketSelling.TicketSelling.Service.SeatCategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat-categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatCategoryController {
    SeatCategoryService seatCategoryService;

    @GetMapping("/{id}")
    public ApiResponse<SeatCategoryResponse> getSeatCategory(@PathVariable String id) {
        return ApiResponse.<SeatCategoryResponse>builder()
                .result(seatCategoryService.getSeatCategory(id))
                .build();
    }

    @GetMapping("/{seatCategoryId}/seats")
    public ApiResponse<List<SeatResponse>>  getAvailableSeats(
            @PathVariable String seatCategoryId,
            @RequestParam(defaultValue = "AVAILABLE", required = false) SeatStatus seatStatus
            ) {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatCategoryService.getStatusSeats(seatCategoryId, seatStatus))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SeatCategoryResponse>> getAllSeatCategories() {
        return ApiResponse.<List<SeatCategoryResponse>>builder()
                .result(seatCategoryService.getAllSeatCategories())
                .build();
    }

    @GetMapping("/by-concert/{concertId}")
    public ApiResponse<List<SeatCategoryResponse>> getAllSeatsByConcertId(@PathVariable String concertId) {
        return ApiResponse.<List<SeatCategoryResponse>>builder()
                .result(seatCategoryService.getAllSeatsByConcertId(concertId))
                .build();
    }

    @PostMapping()
    public ApiResponse<SeatCategoryResponse> createNewSeatCategory(@RequestParam String hallId,
                                                                   @Valid @RequestBody SeatCategoryCreationRequest request) {
        return ApiResponse.<SeatCategoryResponse>builder()
                .result(seatCategoryService.createNewSeatCategory(hallId, request))
                .build();
    }

    @PostMapping("/list-seat/{hallId}")
    public ApiResponse<List<SeatCategoryResponse>> createNewListSeatCategory(@PathVariable String hallId,
                                                                   @Valid @RequestBody List<SeatCategoryCreationRequest> requests) {
        System.out.println(hallId);
        return ApiResponse.<List<SeatCategoryResponse>>builder()
                .result(seatCategoryService.createNewListSeatCategory(hallId, requests))
                .build();
    }


    @PutMapping("/{id}")
    public ApiResponse<SeatCategoryResponse> updateSeatCategory(@PathVariable String id,
                                                                @Valid @RequestBody SeatCategoryUpdateRequest request) {
        return ApiResponse.<SeatCategoryResponse>builder()
                .result(seatCategoryService.updateSeatCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSeat(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .message("Seat category with ID " + id + " is successfully deleted")
                .build();
    }
}
