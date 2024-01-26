package com.cengizhanyavuz.flightsearchapi.business.service;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlightService<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D flightServiceCreate(D d);

    // LIST
    public List<D> flightServiceList(Long id);

    // FIND BY
    public D flightServiceFindById(Long id);

    // UPDATE
    public D flightServiceUpdate(Long id,D d);

    // DELETE
    public D flightServiceDeleteById(Long id);

    FlightSearchResult searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
}
