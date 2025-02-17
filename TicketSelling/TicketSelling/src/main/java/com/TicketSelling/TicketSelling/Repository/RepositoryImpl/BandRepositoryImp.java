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

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandRepositoryImp implements IBandRepository {
    BandJpaRepository bandJpaRepository;



    @Override
    public List<Band> getAllBands() {
        return bandJpaRepository.findAllBand();
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
    public void deleteBand(Band band) {
        band.setDeletedAt(LocalDateTime.now());
        bandJpaRepository.save(band);
    }

    @Override
    public Band findBandById(String bandId) {
        System.out.println("execute finding " + bandId);
        return bandJpaRepository.findBandById(bandId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

}
