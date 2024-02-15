package com.cengizhanyavuz.flightsearchapi.exception;

public record ErrorResponse (
        String success,
        java.lang.Error error
) { }