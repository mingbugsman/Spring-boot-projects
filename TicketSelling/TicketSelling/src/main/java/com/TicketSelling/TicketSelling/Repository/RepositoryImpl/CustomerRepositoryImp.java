package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Enum.SortOrder;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomCustomerMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomerMapper;
import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.CustomerJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerRepositoryImp implements ICustomerRepository {
    CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer save(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public Object[] getCustomerConcertHistory(String customerId) {
        return customerJpaRepository.getCustomerConcerts(customerId);

    }

    @Override
    public Customer getCustomerBookingResponse(String customerId) {
        return getCustomerById(customerId);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerJpaRepository.getAllCustomers();
    }

    @Override
    public List<Customer> getAllCustomersByConcertId(String concertId, LocalDateTime lastCreatedAt,String sortDirection, Pageable pageable) {

            return customerJpaRepository.getCustomersByConcertIdSeekPagination(
                    concertId,lastCreatedAt, sortDirection,pageable);

    }


    @Override
    public void deleteCustomer(Customer customer) {
        customer.setDeletedAt(LocalDateTime.now());
        System.out.println("Deleting customer: " + customer.getId() + ", deletedAt=" + customer.getDeletedAt());
        customerJpaRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerJpaRepository.findCustomerById(customerId).orElseThrow(() ->
                new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public boolean checkExistsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }


}
