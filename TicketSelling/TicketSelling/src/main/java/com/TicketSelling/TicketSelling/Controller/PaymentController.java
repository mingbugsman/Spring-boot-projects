package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.Payment.PaymentRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Payment.ShowInfoPaymentRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Payment.PaymentInformation;
import com.TicketSelling.TicketSelling.DTO.Response.Payment.PaymentResponse;
import com.TicketSelling.TicketSelling.Service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/info")
    public ApiResponse<PaymentInformation> showInfoPayment(@Valid @RequestBody ShowInfoPaymentRequest request) {
        return ApiResponse.<PaymentInformation>builder()
                .result(paymentService.showInfo(request))
                .build();
    }

    @PostMapping("/reserving/{bookingId}")
    public ApiResponse<PaymentResponse> processReserveTicketPayment(@PathVariable String bookingId, @Valid @RequestBody PaymentRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.processReserveTicketPayment(bookingId,request))
                .build();
    }

    // if customer doesn't want to reserve, they can pay for it
    @PostMapping("/pay/ticket")
    public ApiResponse<PaymentResponse> processTicketPayment(
                                                             @Valid @RequestBody PaymentRequest paymentRequest) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.processTicketPayment(paymentRequest))
                .build();
    }

    // If it is a list, the customer needs to pay the previously unordered amounts
    @PostMapping("/pay/listTicket")
    public ApiResponse<PaymentResponse> processListTicketPayment (

            @Valid @RequestBody List<PaymentRequest> paymentListRequest
    ) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.processListTicketPayment(paymentListRequest))
                .build();
    }
}
