package com.TicketSelling.TicketSelling.Controller;


import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.ApiResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @GetMapping
    public ApiResponse<List<CustomerResponse>> getAllCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerService.getAllCustomers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable String id) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getCustomer(id))
                .build();
    }

    @GetMapping("/by-concert/{concertId}")
    public ApiResponse<List<CustomerResponse>> getCustomersByConcertId(
            @PathVariable String concertId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder) {

        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerService.getAllCustomersByConcertId(concertId, lastCreatedAt, sortOrder, pageSize))
                .build();
    }

    @PostMapping
    public ApiResponse<CustomerResponse> createNewCustomer(@Valid @RequestBody CustomerCreationRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createNewCustomer(request))
                .build();
    }

    @PostMapping("/list")
    public ApiResponse<List<CustomerResponse>> createList(@Valid @RequestBody List<CustomerCreationRequest> requests) {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerService.createList(requests))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CustomerResponse> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerUpdateRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ApiResponse.<String>builder()
                .message("Successfully deleted customer with ID: " + id)
                .build();
    }
}
