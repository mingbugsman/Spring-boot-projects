package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomCustomerMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomerMapper;
import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.CustomerJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerRepositoryImp implements ICustomerRepository {
    CustomerJpaRepository customerJpaRepository;
    CustomerMapper customerMapper;
    CustomCustomerMapper customCustomerMapper;

    @Override
    public CustomerConcertHistoryResponse getCustomerConcertHistory(String customerId) {
        Object[] rawData = customerJpaRepository.getCustomerConcerts(customerId);
        return customCustomerMapper.toCustomerConcertHistoryResponse(rawData);
    }

    @Override
    public CustomerBookingsResponse getCustomerBookingResponse(String customerId) {
        Customer customer = getCustomerById(customerId);
        return customCustomerMapper.toCustomerBookingsResponse(customer);
    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        return customerMapper.toCustomerResponse(getCustomerById(customerId));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerJpaRepository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
    }

    @Override
    public CustomerResponse createNewResponse(CustomerCreationRequest request) {
        if (customerJpaRepository.existsByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(request);
        customer = customerJpaRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request) {
        var customer = getCustomerById(customerId);
        customerMapper.updateCustomer(customer, request);
        customer = customerJpaRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        customerJpaRepository.delete(customer);
    }
    @Override
    public Customer getCustomerById(String customerId) {
        return customerJpaRepository.findById(customerId).orElseThrow(() ->
                new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }
}
