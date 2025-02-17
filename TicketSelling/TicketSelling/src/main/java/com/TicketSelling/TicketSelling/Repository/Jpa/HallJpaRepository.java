package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallJpaRepository extends JpaRepository<Hall,String> {
    boolean existsByAddress(String addressHall);

    @Query("SELECT h FROM Hall h " +
            "WHERE h.deletedAt IS NULL")
    List<Hall> getAllHalls();


    @Query("SELECT h FROM Hall h " +
            "WHERE h.id = :hallId AND h.deletedAt IS NULL")
    Optional<Hall> findHallById(@Param("hallId") String hallId);
}
