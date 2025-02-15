package com.TicketSelling.TicketSelling.Repository.Jpa;

import com.TicketSelling.TicketSelling.Entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandJpaRepository extends JpaRepository<Band, String> {
    Band findByBandNameAndCountryAndFoundingYear(String bandName, String country, int foundingYear);
}
