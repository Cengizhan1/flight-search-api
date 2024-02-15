package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.bean.ModelMapperBean;
import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.airport.AirportDetail;
import com.cengizhanyavuz.flightsearchapi.business.service.IAirportService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import com.cengizhanyavuz.flightsearchapi.data.repository.IAirportRepository;
import com.cengizhanyavuz.flightsearchapi.exception.CustomException;
import com.cengizhanyavuz.flightsearchapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class AirportService implements IAirportService<AirportDTO, Airport> {

    private final ModelMapperBean modelMapperBean;
    private final IAirportRepository airportRepository;

    @Override
    public AirportDTO entityToDto(Airport airport) {
        return modelMapperBean.modelMapperMethod().map(airport, AirportDTO.class);
    }

    @Override
    public Airport dtoToEntity(AirportDTO airportDto) {
        return modelMapperBean.modelMapperMethod().map(airportDto, Airport.class);
    }

    @Override
    public List<AirportDTO> airportServiceList() {
        List<Airport> airports = (List<Airport>) airportRepository.findAll();
        List<AirportDTO> airportDtoList = new ArrayList<>();
        for (Airport airport : airports) {
            AirportDTO airportDto = entityToDto(airport);
            airportDtoList.add(airportDto);
        }
        return airportDtoList;
    }

    @Override
    public void airportServiceCreate(AirportDetail[] airportDetail) {
        List<Airport> airportList = new ArrayList<>();
        for (AirportDetail airportDetail1 : airportDetail) {
            if (airportRepository.findByAirportId(airportDetail1.airportId()).isEmpty()) {
                Airport airport = save(airportDetail1);
                airportList.add(airport);
            }
        }
        if (airportList.size() > 0) {
            airportRepository.saveAll(airportList);
        }
    }

    @Override
    public void airportServiceUpdate(AirportDetail[] airportDetail) {
        for (AirportDetail airportDetail1 : airportDetail) {
            AirportDTO airportDTO = airportServiceFindById(airportDetail1.airportId());
            if (airportDTO != null) {
                Airport airport = save(airportDetail1);
                airportRepository.save(airport);
            }
        }
    }

    @Override
    public void airportServiceDelete(AirportDetail[] airportDetail) {
        for (AirportDetail airportDetail1 : airportDetail) {
            Optional<Airport> airport = airportRepository.findByAirportId(airportDetail1.airportId());
            airport.ifPresent(airportRepository::delete);
        }
    }

    private Airport save(AirportDetail airportDetail1) {
        System.out.println("wqdqwd");
        Airport airport = airportRepository.findByAirportId(airportDetail1.airportId()).orElseGet(Airport::new);
        airport.setAirportId(airportDetail1.airportId());
        airport.setCity(airportDetail1.city());
        return airport;
    }

    @Override
    public AirportDTO airportServiceFindById(Long airportId) {
        Airport airport = null;
        if (airportId != null) {
            airport = airportRepository.findByAirportId(airportId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable to find the airport with ID " + airportId));
        } else {
            throw new CustomException("airport id is null");
        }
        return entityToDto(airport);
    }


    private Optional<Airport> findAirportById(List<Airport> airports, Long airportId) {
        return airports
                .stream()
                .filter(airport -> airport.getAirportId().equals(airportId))
                .findFirst();
    }
}
