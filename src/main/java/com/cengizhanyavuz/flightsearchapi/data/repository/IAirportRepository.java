package com.cengizhanyavuz.flightsearchapi.data.repository;

import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAirportRepository extends CrudRepository<Airport,Long> {

    Optional<Airport> findByAirportId(Long airportId);
}