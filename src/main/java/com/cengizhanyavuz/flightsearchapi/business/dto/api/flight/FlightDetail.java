package com.cengizhanyavuz.flightsearchapi.business.dto.api.flight;

import java.time.LocalDateTime;
import java.util.Date;

public record FlightDetail(
        Long flightId,
        Long departureAirportId,
        Long arrivalAirportId,
        String departureDateTime,
        String returnDateTime,
        double price,
        String createdDate,
        String updatedDate
) {}
