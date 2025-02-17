package com.TicketSelling.TicketSelling.Controller;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;

import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Service.BandService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/band")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandController {

    BandService bandService;

    @GetMapping
    public ApiResponse<List<BandResponse>> getAllBands() {
        return ApiResponse.<List<BandResponse>>builder()
                .result(bandService.getAllBands())
                .build();
    }

    @GetMapping("/{seatId}")
    public ApiResponse<BandDetailResponse> getBand(@PathVariable String seatId) {
        return ApiResponse.<BandDetailResponse>builder()
                .result(bandService.getBandDetail(seatId))
                .build();
    }

    @PostMapping
    public ApiResponse<BandResponse> createNewBand(@Valid @RequestBody BandCreationRequest request) {
        return ApiResponse.<BandResponse>builder()
                .result(bandService.createNewBand(request))
                .build();
    }

    @PutMapping("/{seatId}")
    public ApiResponse<BandResponse> updateBand(@PathVariable String seatId,@RequestBody BandUpdateRequest request) {
        return ApiResponse.<BandResponse>builder()
                .result(bandService.updateBand(seatId,request))
                .build();
    }

    @DeleteMapping("/{seatId}")
    public ApiResponse<String> deleteBand(@PathVariable String seatId) {
        bandService.deleteBand(seatId);
        return ApiResponse.<String>builder()
                .result(seatId + " is successfully deleted")
                .build();
    }
}
