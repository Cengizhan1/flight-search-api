package com.cengizhanyavuz.flightsearchapi.business.dto.api;

import java.util.Date;

public record AirportResponse(
        Long airportId,
        String city,
        Date createdDate,
        Date updatedDate
) { }
