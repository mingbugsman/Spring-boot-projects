package com.TicketSelling.TicketSelling.Service;


import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomCustomerMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomTicketMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomerMapper;
import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import com.TicketSelling.TicketSelling.Utils.SortUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    ITicketRepository ticketRepository;
    CustomTicketMapper customTicketMapper;
    ICustomerRepository customerRepository;
    CustomerMapper customerMapper;
    CustomCustomerMapper customCustomerMapper;

    public CustomerConcertHistoryResponse getCustomerConcertHistory(String customerId) {
        Object[] rawData = customerRepository.getCustomerConcertHistory(customerId);
        return customCustomerMapper.toCustomerConcertHistoryResponse(rawData);

    }

    public CustomerBookingsResponse getCustomerBookingResponse(String customerId) {

        Customer customerBooking = customerRepository.getCustomerBookingResponse(customerId);
        return customCustomerMapper.toCustomerBookingsResponse(customerBooking);
    }

    public CustomerResponse getCustomer(String customerId) {
        Customer customer=  customerRepository.getCustomerById(customerId);
        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.getAllCustomers().stream().map(customerMapper::toCustomerResponse).toList();
    }


    public List<TicketDetailResponse> getAllTicketsByCustomerId(String customerId) {
        return ticketRepository.getAllTicketsByCustomerId(customerId)
                .stream().map(customTicketMapper::toTicketDetailResponse).toList();
    }

    public List<CustomerResponse> getAllCustomersByConcertId(String concertId, LocalDateTime lastCreatedAt,SortOrder sortOrder, int pageSize) {
        Sort.Direction sortDirection = sortOrder.toSpringSortOrder();
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(sortDirection, "createdAt"));
        List<Customer> sortedCustomer = customerRepository.getAllCustomersByConcertId(concertId,lastCreatedAt, sortOrder.name(),pageable);
        return  sortedCustomer.stream().map(customerMapper::toCustomerResponse).toList();
    }

    // POST
    public CustomerResponse createNewCustomer(CustomerCreationRequest request) {
        if (customerRepository.checkExistsByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(request);
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> createList(List<CustomerCreationRequest> requests) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (var req : requests) {
            var customerResponse = createNewCustomer(req);
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    // PUT / PATCH
    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request) {
        var customer = customerRepository.getCustomerById(customerId);
        customerMapper.updateCustomer(customer, request);
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    // DELETE
    public void deleteCustomer(String customerId) {
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer.getDeletedAt() == null) {
            customerRepository.deleteCustomer(customer);
        } else {
            throw new ApplicationException(ErrorCode.ENTITY_IS_ALREADY_DELETED);
        }

    }







}
