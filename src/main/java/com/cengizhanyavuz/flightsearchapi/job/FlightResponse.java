package com.cengizhanyavuz.flightsearchapi.job;

import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;

import java.util.List;

public class FlightResponse {
    private List<FlightDTO> flights;

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }
}
