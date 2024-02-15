package com.cengizhanyavuz.flightsearchapi.business.service;

import com.cengizhanyavuz.flightsearchapi.business.dto.api.airport.AirportDetail;

import java.util.List;

public interface IAirportService<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public void airportServiceCreate(AirportDetail[] airportDetail);

    public void airportServiceUpdate(AirportDetail[] airportDetail);

    public void airportServiceDelete(AirportDetail[] airportDetail);

    // LIST
    public List<D> airportServiceList();

    // FIND BY
    public D airportServiceFindById(Long airportId);
}
