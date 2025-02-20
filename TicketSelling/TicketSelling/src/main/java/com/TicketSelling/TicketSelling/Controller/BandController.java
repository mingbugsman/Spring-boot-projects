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
@RequestMapping("/api/bands")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandController {

    BandService bandService;

    @GetMapping
    public ApiResponse<List<BandResponse>> getAllBands() {
        return ApiResponse.<List<BandResponse>>builder()
                .result(bandService.getAllBands())
                .build();
    }

    @GetMapping("/{bandId}")
    public ApiResponse<BandDetailResponse> getBand(@PathVariable String bandId) {
        return ApiResponse.<BandDetailResponse>builder()
                .result(bandService.getBandDetail(bandId))
                .build();
    }

    @PostMapping
    public ApiResponse<BandResponse> createNewBand(@Valid @RequestBody BandCreationRequest request) {
        return ApiResponse.<BandResponse>builder()
                .result(bandService.createNewBand(request))
                .build();
    }
    @PostMapping("/list")
    public ApiResponse<List<BandResponse>> createNewListBand(@Valid @RequestBody List<BandCreationRequest> requests) {
        return ApiResponse.<List<BandResponse>>builder()
                .result(bandService.createNewListBand(requests))
                .build();
    }

    @PutMapping("/{bandId}")
    public ApiResponse<BandResponse> updateBand(@PathVariable String bandId,@RequestBody BandUpdateRequest request) {
        return ApiResponse.<BandResponse>builder()
                .result(bandService.updateBand(bandId,request))
                .build();
    }

    @DeleteMapping("/{bandId}")
    public ApiResponse<String> deleteBand(@PathVariable String bandId) {
        bandService.deleteBand(bandId);
        return ApiResponse.<String>builder()
                .result(bandId + " is successfully deleted")
                .build();
    }
}
