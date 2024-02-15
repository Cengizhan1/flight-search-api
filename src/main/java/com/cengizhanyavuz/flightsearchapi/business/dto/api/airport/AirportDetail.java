package com.cengizhanyavuz.flightsearchapi.business.dto.api.airport;

import java.util.Date;

public record AirportDetail(
        Long airportId,
        String city,
        String createdDate,
        String updatedDate
) {}
