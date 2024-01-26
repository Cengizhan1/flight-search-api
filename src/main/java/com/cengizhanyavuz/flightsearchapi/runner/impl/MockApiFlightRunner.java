package com.cengizhanyavuz.flightsearchapi.runner.impl;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDto;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;
import com.cengizhanyavuz.flightsearchapi.job.AirportResponse;
import com.cengizhanyavuz.flightsearchapi.job.FlightResponse;
import com.cengizhanyavuz.flightsearchapi.runner.IFlightRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name = "flight-api.enable-mock-api", havingValue = "true")
public class MockApiFlightRunner implements IFlightRunner {
    @Value("${flight-api.url}")
    private String API_URL;
    @Override
    public void start() throws Exception {
        System.out.println("MockApiFlightRunner started");
        getFlights();
        getAirports();
    }
    public void getFlights() {
        FlightResponse flightResponse = fetchData(API_URL, FlightResponse.class);
        List<FlightDTO> flights = flightResponse.getFlights();
    }
    public void getAirports() {
        AirportResponse airportResponse = fetchData(API_URL, AirportResponse.class);
        List<AirportDto> airports = airportResponse.getAirports();
    }


    private <T> T fetchData(String apiUrl, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity =
                restTemplate.getForEntity(apiUrl, responseType);
        return responseEntity.getBody();
    }

}
