package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomerMapper;
import com.TicketSelling.TicketSelling.Repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    BookingMapper bookingMapper;

   /* public CustomerConcertHistoryResponse getCustomerConcertHistory(String customerId) {
        Customer customer = getCustomerById(customerId);

    }*/
/*
    public CustomerBookingsResponse getCustomerBookingResponse(String customerId) {
        Customer customer = getCustomerById(customerId);
        return customerMapper.toCustomerBookingResponse(customer);
    }*/

    public CustomerResponse getCustomer(String customerId) {
        return customerMapper.toCustomerResponse(getCustomerById(customerId));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
    }

    // POST
    public CustomerResponse createNewCustomer(CustomerCreationRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(request);
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    // PUT / PATCH
    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request) {

        var customer = getCustomerById(customerId);
        customerMapper.updateCustomer(customer, request);
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);

    }

    // DELETE
    public void deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.delete(customer);
    }

    private Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).orElseThrow(() ->
                new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }





}
