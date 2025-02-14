package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;

import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    ICustomerRepository customerRepository;


    public CustomerConcertHistoryResponse getCustomerConcertHistory(String customerId) {
        return customerRepository.getCustomerConcertHistory(customerId);

    }

    public CustomerBookingsResponse getCustomerBookingResponse(String customerId) {
        return customerRepository.getCustomerBookingResponse(customerId);
    }

    public CustomerResponse getCustomer(String customerId) {
        return customerRepository.getCustomer(customerId);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    // POST
    public CustomerResponse createNewCustomer(CustomerCreationRequest request) {
        return customerRepository.createNewResponse(request);
    }

    // PUT / PATCH
    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request) {
        return customerRepository.updateCustomer(customerId, request);

    }

    // DELETE
    public void deleteCustomer(String customerId) {
        customerRepository.deleteCustomer(customerId);
    }







}
