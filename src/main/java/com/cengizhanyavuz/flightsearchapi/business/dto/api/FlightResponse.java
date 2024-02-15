package com.cengizhanyavuz.flightsearchapi.business.dto.api;

import java.time.LocalDateTime;
import java.util.Date;

public record FlightResponse(
    Long flightId,
    Long departureAirportId,
    Long arrivalAirportId,
    LocalDateTime departureDateTime,
    LocalDateTime returnDateTime,
    double price,
    Date createdDate,
    Date updatedDate
) { }