package com.cengizhanyavuz.flightsearchapi.business.service;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IFlightService<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public void flightServiceCreateOrUpdate(List<D> d);

    public List<D> flightServiceList();


    // FIND BY
    public D flightServiceFindById(Long flightId);

    // UPDATE
    public D flightServiceUpdate(Long id,D d);

    // DELETE
    public D flightServiceDeleteById(Long id);

    FlightSearchResult searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime, Optional<LocalDateTime> returnDateTime);
}
