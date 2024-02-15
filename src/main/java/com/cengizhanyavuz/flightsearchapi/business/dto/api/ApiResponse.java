package com.cengizhanyavuz.flightsearchapi.business.dto.api;

import com.cengizhanyavuz.flightsearchapi.business.dto.api.airport.AirportResponse;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.flight.FlightResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record ApiResponse(
        FlightResponse flights,
        AirportResponse airports
){ }
