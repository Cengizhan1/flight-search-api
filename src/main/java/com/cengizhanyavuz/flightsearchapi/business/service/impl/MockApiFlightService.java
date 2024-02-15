package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.ApiResponse;
import com.cengizhanyavuz.flightsearchapi.business.service.IAirportService;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightRunner;
import com.cengizhanyavuz.flightsearchapi.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(name = "flight-api.enable-mock-api", havingValue = "true")
public class MockApiFlightService implements IFlightRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MockApiFlightService.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final IAirportService<AirportDTO, Airport> airportService;
    private final IFlightService<FlightDTO, Flight> flightService;

    public MockApiFlightService(RestTemplate restTemplate, IAirportService<AirportDTO, Airport> airportService, IFlightService<FlightDTO, Flight> flightService) {
        this.restTemplate = restTemplate;
        this.airportService = airportService;
        this.flightService = flightService;
    }

    @Value("${flight-api.url}")
    private String API_URL;

    @Override
    public void start() throws Exception {
        System.out.println("MockApiFlightRunner started");
        ApiResponse apiResponse = fetchData(API_URL);
        if (apiResponse != null && apiResponse.flights() != null) {
            if (apiResponse.flights().create() != null) {
                flightService.flightServiceCreate(apiResponse.flights().create());
            }
            if (apiResponse.flights().update() != null) {
                flightService.flightServiceUpdate(apiResponse.flights().update());
            }
            if (apiResponse.flights().delete() != null) {
                flightService.flightServiceDelete(apiResponse.flights().delete());
            }
        }
    }

    private ApiResponse fetchData(String apiUrl) {
        LOG.info("Requesting api: " + apiUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        try {
            ApiResponse apiResponse = objectMapper.readValue(responseEntity.getBody(), ApiResponse.class);
            return apiResponse;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}


