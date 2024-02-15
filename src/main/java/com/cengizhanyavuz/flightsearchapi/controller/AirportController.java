package com.cengizhanyavuz.flightsearchapi.controller;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDTO;
import com.cengizhanyavuz.flightsearchapi.business.service.IAirportService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/airport/api/v1")
@Api(value = "Airport Controller", tags = "Airport API")
public class AirportController {

    private final IAirportService<AirportDTO, Airport> airportService;

    @RateLimiter(name = "basic")
    @GetMapping("/list")
    @ApiOperation(value = "Get the list of airports", notes = "Retrieves a list of all airports")
    public ResponseEntity<List<AirportDTO>> airportServiceList() {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.airportServiceList());
    }
    @RateLimiter(name = "basic")
    @GetMapping("/airport/{id}")
    @ApiOperation(value = "Get airport by ID", notes = "Retrieves an airport by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved airport"),
            @ApiResponse(code = 404, message = "Airport not found")
    })
    public ResponseEntity<AirportDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(airportService.airportServiceFindById(id));
    }
}
