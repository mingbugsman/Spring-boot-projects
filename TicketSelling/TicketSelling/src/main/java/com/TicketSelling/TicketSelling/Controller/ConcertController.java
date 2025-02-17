package com.TicketSelling.TicketSelling.Controller;

import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Concert.ConcertUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Service.ConcertService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concert")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertController {

    ConcertService concertService;

    @GetMapping
    public ApiResponse<List<ConcertResponse>> getAllConcerts() {
        return ApiResponse.<List<ConcertResponse>>builder()
                .result(concertService.getAllConcerts())
                .build();
    }

    @GetMapping("/{concertId}")
    public ApiResponse<ConcertDetailResponse> getConcertDetail(@PathVariable String concertId) {
        return ApiResponse.<ConcertDetailResponse>builder()
                .result(concertService.getConcertDetail(concertId))
                .build();
    }

    @PostMapping
    public ApiResponse<ConcertResponse> createNewConcert(@RequestBody @Valid ConcertCreationRequest request) {
        return ApiResponse.<ConcertResponse>builder()
                .result(concertService.createNewConcert(request))
                .build();
    }

    @PutMapping("/{concertId}")
    public ApiResponse<ConcertResponse> updateConcert(@PathVariable String concertId, @RequestBody ConcertUpdateRequest request) {
        System.out.println("update concert with id : " + concertId);
        return ApiResponse.<ConcertResponse>builder()
                .result(concertService.updateConcert(concertId, request))
                .build();
    }

    @DeleteMapping("/{concertId}")
    public ApiResponse<String> deleteConcert(@PathVariable String concertId) {
        concertService.deleteConcert(concertId);
        return ApiResponse.<String>builder()
                .result(concertId + " is successfully deleted")
                .build();
    }


}
