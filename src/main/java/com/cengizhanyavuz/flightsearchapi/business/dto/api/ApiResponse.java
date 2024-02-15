package com.cengizhanyavuz.flightsearchapi.business.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        FlightResponse flights,
        AirportResponse airports
){ }
