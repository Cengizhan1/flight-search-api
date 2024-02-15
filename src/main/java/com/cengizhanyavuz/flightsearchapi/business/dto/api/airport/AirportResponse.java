package com.cengizhanyavuz.flightsearchapi.business.dto.api.airport;

import java.util.Date;

public record AirportResponse(
        AirportDetail[] create,
        AirportDetail[] update,
        AirportDetail[] delete
) { }
