package com.cengizhanyavuz.flightsearchapi.runner.impl;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDto;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.service.IAirportService;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.AirportResponse;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.FlightResponse;
import com.cengizhanyavuz.flightsearchapi.runner.IFlightRunner;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "flight-api.enable-mock-api", havingValue = "true")
public class MockApiFlightRunner implements IFlightRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MockApiFlightRunner.class);
    private final IAirportService<AirportDto, Airport> airportService;
    private final IFlightService<FlightDTO, Flight> flightService;

    @Value("${flight-api.url}")
    private String API_URL;
    @Override
    public void start() throws Exception {
        System.out.println("MockApiFlightRunner started");
        getAirports();
        getFlights();
    }
    public void getFlights() {
        try {
            FlightResponse flightResponse = fetchData(API_URL, FlightResponse.class);
            List<FlightDTO> flights = flightResponse.getFlights();
            flightService.flightServiceCreateOrUpdate(flights);
        } catch (RestClientException ex) {
            LOG.error("Error occurred while fetching flights: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void getAirports() {
        try {
            AirportResponse airportResponse = fetchData(API_URL, AirportResponse.class);
            List<AirportDto> airports = airportResponse.getAirports();
            airportService.airportServiceCreateOrUpdate(airports);
        } catch (RestClientException ex) {
            LOG.error("Error occurred while fetching airports: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }


    private <T> T fetchData(String apiUrl, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity =
                restTemplate.getForEntity(apiUrl, responseType);
        return responseEntity.getBody();
    }

}


