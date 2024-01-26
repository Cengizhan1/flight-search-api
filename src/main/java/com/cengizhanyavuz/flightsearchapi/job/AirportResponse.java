package com.cengizhanyavuz.flightsearchapi.job;

import com.cengizhanyavuz.flightsearchapi.business.dto.AirportDto;

import java.util.List;

public class AirportResponse {
    private List<AirportDto> airports;

    public List<AirportDto> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDto> airports) {
        this.airports = airports;
    }
}
