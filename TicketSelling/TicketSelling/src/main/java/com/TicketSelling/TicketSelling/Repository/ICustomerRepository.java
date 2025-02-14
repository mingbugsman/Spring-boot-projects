package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ICustomerRepository {
    CustomerConcertHistoryResponse getCustomerConcertHistory(String customerId);
    CustomerBookingsResponse getCustomerBookingResponse(String customerId);
    CustomerResponse getCustomer(String customerId);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse createNewResponse(CustomerCreationRequest request);
    CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request);
    void deleteCustomer(String customerId);
    Customer getCustomerById(String customerId);
}
