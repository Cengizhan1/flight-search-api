package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.bean.ModelMapperBean;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import com.cengizhanyavuz.flightsearchapi.data.repository.IFlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class FlightService implements IFlightService<FlightDTO, Flight> {

    private final ModelMapperBean modelMapperBean;
    private final IFlightRepository flightRepository;
    @Override
    public FlightDTO entityToDto(Flight flight) {
        return modelMapperBean.modelMapperMethod().map(flight, FlightDTO.class);
    }

    @Override
    public Flight dtoToEntity(FlightDTO flightDTO) {
        return modelMapperBean.modelMapperMethod().map(flightDTO, Flight.class);
    }

    @Override
    public FlightDTO flightServiceCreate(FlightDTO flightDTO) {
        return null;
    }

    @Override
    public List<FlightDTO> flightServiceList(Long id) {
        return null;
    }

    @Override
    public FlightDTO flightServiceFindById(Long id) {
        return null;
    }

    @Override
    public FlightDTO flightServiceUpdate(Long id, FlightDTO flightDTO) {
        return null;
    }

    @Override
    public FlightDTO flightServiceDeleteById(Long id) {
        return null;
    }
}
