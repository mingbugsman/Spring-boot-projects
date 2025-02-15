package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface ICustomerRepository {
    Customer save(Customer customer);
    Object[] getCustomerConcertHistory(String customerId);
    Customer getCustomerBookingResponse(String customerId);
    List<Customer> getAllCustomers();
    List<Customer> getAllCustomersByConcertId(String concertId, LocalDateTime lastCreatedAt, String sortOrder, Pageable pageSize);
    void deleteCustomer(Customer customer);
    Customer getCustomerById(String customerId);
    boolean checkExistsByEmail(String email);
}
