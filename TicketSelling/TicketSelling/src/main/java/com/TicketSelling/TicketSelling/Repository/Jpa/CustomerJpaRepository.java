package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, String> {
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


    @Query("""
    SELECT c FROM Customer c
    JOIN c.bookings b
    JOIN b.tickets t
    JOIN t.concert co
    WHERE co.id = :concertId AND co.deletedAt IS NULL AND c.deletedAt IS NULL AND b.deletedAt IS NULL AND t.deletedAt IS NULL
    AND (:lastCreatedAt IS NULL OR
        (:sortDirection = 'ASC' AND c.createdAt > :lastCreatedAt) OR
        (:sortDirection = 'DESC' AND c.createdAt < :lastCreatedAt))
    ORDER BY
        CASE WHEN :sortDirection = 'ASC' THEN c.createdAt END ASC,
        CASE WHEN :sortDirection = 'DESC' THEN c.createdAt END DESC
    """)
    List<Customer> getCustomersByConcertIdSeekPagination(
            @Param("concertId") String concertId,
            @Param("lastCreatedAt") LocalDateTime lastCreatedAt,
            @Param("sortDirection") String sortDirection,
            Pageable pageable
    );

    @Query("SELECT c FROM Customer c " +
            "WHERE c.deletedAt IS NULL")
    List<Customer> getAllCustomers();

    @Query("SELECT c FROM Customer c " +
            "WHERE c.id = :customerId AND c.deletedAt IS NULL")
    Optional<Customer> findCustomerById(@Param("customerId") String customerId);




}
