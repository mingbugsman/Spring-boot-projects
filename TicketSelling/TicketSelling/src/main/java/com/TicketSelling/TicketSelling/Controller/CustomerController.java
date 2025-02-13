package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @GetMapping
    public ApiResponse<List<CustomerResponse>> getAllCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerService.getAllCustomers())
                .build();
    }

    @GetMapping("/{customerId}")
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getCustomer(customerId))
                .build();
    }

    @PostMapping
    public ApiResponse<CustomerResponse> createNewCustomer(@Valid @RequestBody CustomerCreationRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createNewCustomer(request))
                .build();
    }
}
