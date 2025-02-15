package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Band.BandCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Entity.Band;

import java.util.List;

public interface IBandRepository {

    Band save(Band band);
    List<Band> getAllBands();
    Band findByBandNameAndCountryAndFoundingYear(String bandName, String country, int foundingYear);
    void deleteBand(Band band);
    Band findBandById(String bandId);
}
