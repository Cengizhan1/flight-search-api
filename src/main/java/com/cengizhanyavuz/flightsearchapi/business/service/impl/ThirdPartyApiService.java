package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.business.service.IFlightRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "flight-api.enable-mock-api", havingValue = "false")
public class ThirdPartyApiService implements IFlightRunner {
    @Override
    public void start() throws Exception {
        System.out.println("ThirdPartyApiRunner started");
    }
}
