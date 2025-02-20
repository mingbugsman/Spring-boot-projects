package com.TicketSelling.TicketSelling.Controller;

import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Hall.HallUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Hall.HallResponse;
import com.TicketSelling.TicketSelling.Service.HallService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HallController {

    HallService hallService;

    @GetMapping
    public ApiResponse< List<HallResponse> > getAllHalls() {
        return ApiResponse.<List<HallResponse>>builder()
                .result(hallService.getAllHalls())
                .build();
    }

    @GetMapping("{hallId}")
    public ApiResponse<HallDetailResponse> getHallDetail(@PathVariable String hallId) {
        return ApiResponse.<HallDetailResponse>builder()
                .result(hallService.getHallDetail(hallId))
                .build();
    }

    @PostMapping
    public ApiResponse<HallResponse> createNewHall(@Valid @RequestBody HallCreationRequest request) {
        return ApiResponse.<HallResponse>builder()
                .result(hallService.createNewHall(request))
                .build();
    }
    @PostMapping("/list")
    public ApiResponse<List<HallResponse>> createListHall(@RequestBody List<HallCreationRequest> requests) {
        return ApiResponse.<List<HallResponse>>builder()
                .result(hallService.createNewListHall(requests))
                .build();
    }

    @PutMapping("/{hallId}")
    public ApiResponse<HallResponse> updateHall(@PathVariable String hallId, @RequestBody HallUpdateRequest request) {
        return ApiResponse.<HallResponse>builder()
                .result(hallService.updateHall(hallId,request))
                .build();
    }

    @DeleteMapping("/{hallId}")
    public ApiResponse<String> deleteHall(@PathVariable String hallId) {
        hallService.deleteHall(hallId);
        return ApiResponse.<String>builder()
                .result(hallId + " is successfully deleted")
                .build();
    }



}
