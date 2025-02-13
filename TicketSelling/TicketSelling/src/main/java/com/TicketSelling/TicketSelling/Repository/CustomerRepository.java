package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByEmail(String email);


    @Query(
            "SELECT c.id, c.customerName, co " +
                    "FROM Customer c " +
                    "JOIN c.bookings b " +
                    "JOIN b.tickets t " +
                    "JOIN t.concert co " +
                    "WHERE c.id = :customerId"
    )
    Object[] getCustomerConcerts(@Param("customerId") String customerId);


}
