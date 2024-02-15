package com.cengizhanyavuz.flightsearchapi.business.dto.api.flight;

import java.time.LocalDateTime;
import java.util.Date;

public record FlightResponse(
     FlightDetail[] create,
     FlightDetail[] update,
     FlightDetail[] delete
) { }