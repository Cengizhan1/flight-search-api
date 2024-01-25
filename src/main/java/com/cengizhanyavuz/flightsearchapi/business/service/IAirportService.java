package com.cengizhanyavuz.flightsearchapi.business.service;

import java.util.List;

public interface IAirportService<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D airportServiceCreate(D d);

    // LIST
    public List<D> airportServiceList(Long id);

    // FIND BY
    public D airportServiceFindById(Long id);

    // UPDATE
    public D airportServiceUpdate(Long id,D d);

    // DELETE
    public D airportServiceDeleteById(Long id);
}
