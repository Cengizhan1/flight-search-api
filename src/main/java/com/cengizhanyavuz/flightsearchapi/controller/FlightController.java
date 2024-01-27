package com.cengizhanyavuz.flightsearchapi.controller;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/flight/api/v1")
public class FlightController {

    private final IFlightService<FlightDTO, Flight> flightService;

    // CREATE
    @GetMapping(value = "/list")
    public ResponseEntity<List<FlightDTO>> flightServiceList() {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.flightServiceList());
    }

    // FIND BY ID
    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(flightService.flightServiceFindById(id));
    }
    // SEARCH FLIGHTS
    @GetMapping("/search")
    public ResponseEntity<FlightSearchResult> searchFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId,
                       @RequestParam LocalDateTime departureDateTime, @RequestParam LocalDateTime returnDateTime) {

        return ResponseEntity.status(200).body(flightService.searchFlights(departureAirportId, arrivalAirportId, departureDateTime, returnDateTime));
    }

}
