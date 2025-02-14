package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;


import com.TicketSelling.TicketSelling.DTO.Request.Band.BandCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BandMapper;
import com.TicketSelling.TicketSelling.Mapper.CustomMapper.CustomBandMapper;
import com.TicketSelling.TicketSelling.Repository.IBandRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.BandJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandRepositoryImp implements IBandRepository {
    BandJpaRepository bandJpaRepository;



    @Override
    public List<Band> getAllBands() {
        return bandJpaRepository.findAll();
    }

    @Override
    public Band findByBandNameAndCountryAndFoundingYear(String bandName, String country, int foundingYear) {
        return bandJpaRepository.findByBandNameAndCountryAndFoundingYear(bandName, country,foundingYear);
    }

    @Override
    public Band save(Band band) {
       return bandJpaRepository.save(band);
    }


    @Override
    public void deleteBand(String bandId) {
        if (bandJpaRepository.existsById(bandId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        bandJpaRepository.deleteById(bandId);
    }

    @Override
    public Band findBandById(String bandId) {
        return bandJpaRepository.findById(bandId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

}
