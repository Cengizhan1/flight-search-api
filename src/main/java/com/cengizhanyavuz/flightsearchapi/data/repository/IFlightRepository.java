package com.cengizhanyavuz.flightsearchapi.data.repository;

import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface IFlightRepository extends CrudRepository<Flight,Long> {
    Iterable<Flight> findAllByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(
            Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime);

}