package com.cengizhanyavuz.flightsearchapi.exception;

public record Error (
        String code,
        String type,
        String info
) { }