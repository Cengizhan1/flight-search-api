package com.cengizhanyavuz.flightsearchapi.business.service;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDto;

import java.util.List;

public interface IAirportService<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public int airportServiceCreate(List<AirportDto> airports);

    // LIST
    public List<D> airportServiceList();

    // FIND BY
    public D airportServiceFindById(Long airportId);

    // UPDATE
    public D airportServiceUpdate(Long id,D d);

    // DELETE
    public D airportServiceDeleteById(Long airportId);
}
