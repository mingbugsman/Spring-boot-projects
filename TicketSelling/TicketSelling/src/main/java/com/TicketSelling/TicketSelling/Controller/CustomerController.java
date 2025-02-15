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
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/{concertId}/list")
    public ApiResponse<List<CustomerResponse>> getCustomersByConcertId(
            @PathVariable String concertId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") SortOrder sortOrder) {



        return ApiResponse.<List<CustomerResponse>>builder()
                .result(
                        customerService.getAllCustomersByConcertId(concertId, lastCreatedAt, sortOrder, pageSize)
                ).build();



    }

    @PostMapping
    public ApiResponse<CustomerResponse> createNewCustomer(@Valid @RequestBody CustomerCreationRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createNewCustomer(request))
                .build();
    }
    @PutMapping("/{customerId}")
    public ApiResponse<CustomerResponse> updateCustomer(@PathVariable String customerId ,@Valid @RequestBody CustomerUpdateRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customerId, request))
                .build();
    }

    @DeleteMapping("/{customerId}")
    public ApiResponse<CustomerResponse> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ApiResponse.<CustomerResponse>builder()
                .message("successfully delete customer with id : "+ customerId)
                .build();
    }
}
