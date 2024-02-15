package com.cengizhanyavuz.flightsearchapi.controller;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/flight/api/v1")
@Api(value = "Flight API", tags = {"Flight"})
public class FlightController {

    private final IFlightService<FlightDTO, Flight> flightService;

    // CREATE
    @RateLimiter(name = "basic")
    @GetMapping(value = "/list")
    @ApiOperation(value = "Get the list of flights", response = FlightDTO.class, responseContainer = "List")
    public ResponseEntity<List<FlightDTO>> flightServiceList() {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.flightServiceList());
    }

    // FIND BY ID
    @RateLimiter(name = "basic")
    @GetMapping("/flight/{id}")
    @ApiOperation(value = "Get a flight by ID", response = FlightDTO.class)
    public ResponseEntity<FlightDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(flightService.flightServiceFindById(id));
    }

    // SEARCH FLIGHTS
    @RateLimiter(name = "basic")
    @GetMapping("/search")
    @ApiOperation(value = "Search flights", response = FlightSearchResult.class)
    public ResponseEntity<FlightSearchResult> searchFlights(
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam LocalDateTime departureDateTime,
            @RequestParam Optional<LocalDateTime> returnDateTime
    ) {
        return ResponseEntity.status(200).body(flightService.searchFlights(departureAirportId, arrivalAirportId, departureDateTime, returnDateTime));
    }
}
