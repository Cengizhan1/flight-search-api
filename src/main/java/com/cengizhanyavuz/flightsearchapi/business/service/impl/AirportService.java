package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.bean.ModelMapperBean;
import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDto;
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

@RequiredArgsConstructor
@Log4j2
@Service
public class AirportService implements IAirportService<AirportDto, Airport> {

    private final ModelMapperBean modelMapperBean;
    private final IAirportRepository airportRepository;

    @Override
    public AirportDto entityToDto(Airport airport) {
        return modelMapperBean.modelMapperMethod().map(airport, AirportDto.class);
    }

    @Override
    public Airport dtoToEntity(AirportDto airportDto) {
        return modelMapperBean.modelMapperMethod().map(airportDto, Airport.class);
    }

    @Override
    public AirportDto airportServiceCreate(AirportDto airportDto) {
        if (airportDto != null) {
            Airport airport = dtoToEntity(airportDto);
            airportRepository.save(airport);
            airportDto.setId(airport.getId());
        } else {
            throw new NullPointerException("airportDto is null");
        }
        return airportDto;
    }

    @Override
    public List<AirportDto> airportServiceList(Long id) {
        Iterable<Airport> airports = airportRepository.findAll();
        List<AirportDto> airportDtoList = new ArrayList<>();
        for (Airport airport : airports) {
            airportDtoList.add(entityToDto(airport));
        }
        return airportDtoList;
    }

    @Override
    public AirportDto airportServiceFindById(Long id) {
        Airport airport = null;
        if (id != null) {
            airport = airportRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable to find the airport with ID " + id));
        } else {
            throw new CustomException("airport id is null");
        }
        return entityToDto(airport);
    }

    @Override
    public AirportDto airportServiceUpdate(Long id, AirportDto airportDto) {
        AirportDto airportFindDto = airportServiceFindById(id);
        if (airportFindDto != null) {
            Airport airport = dtoToEntity(airportFindDto);
            airport.setId(id);
            airport.setCity(airportDto.getCity());
            airportRepository.save(airport);
            airportDto.setId(airport.getId());
        }else {
            throw new NullPointerException("Unable to find the airport with ID " + id);
        }
        return airportDto;
    }

    @Override
    public AirportDto airportServiceDeleteById(Long id) {
        AirportDto airportFindDto = airportServiceFindById(id);
        if (airportFindDto != null) {
            Airport airport = dtoToEntity(airportFindDto);
            airportRepository.delete(airport);
        }else {
            throw new NullPointerException("Unable to find the airport with ID " + id);
        }
        return airportFindDto;
    }
}
