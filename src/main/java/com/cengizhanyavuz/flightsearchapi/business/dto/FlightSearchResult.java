package com.cengizhanyavuz.flightsearchapi.business.dto;

import java.util.List;

public class FlightSearchResult {
    private List<FlightDTO> outboundFlights;
    private List<FlightDTO> returnFlights;

    public FlightSearchResult(List<FlightDTO> outboundFlights, List<FlightDTO> returnFlights) {
        this.outboundFlights = outboundFlights;
        this.returnFlights = returnFlights;
    }

    public List<FlightDTO> getOutboundFlights() {
        return outboundFlights;
    }

    public List<FlightDTO> getReturnFlights() {
        return returnFlights;
    }
}
