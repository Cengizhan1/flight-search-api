package com.cengizhanyavuz.flightsearchapi.controller;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/flight/api/v1")
public class FlightController {

    private final IFlightService<FlightDTO, Flight> flightService;

    // CREATE
    @PostMapping("/flight")
    public ResponseEntity<FlightDTO> create(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(200).body(flightService.flightServiceCreate(flightDTO));
    }
    // LIST

    // FIND BY ID
    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(flightService.flightServiceFindById(id));
    }

    // DELETE BY ID
    @DeleteMapping("/flight/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(flightService.flightServiceDeleteById(id));
    }

    // SEARCH FLIGHTS
    @GetMapping("/search")
    public ResponseEntity<FlightSearchResult> searchFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId,
                       @RequestParam LocalDateTime departureDateTime, @RequestParam LocalDateTime returnDateTime) {

        return ResponseEntity.status(200).body(flightService.searchFlights(departureAirportId, arrivalAirportId, departureDateTime, returnDateTime));
    }

}
