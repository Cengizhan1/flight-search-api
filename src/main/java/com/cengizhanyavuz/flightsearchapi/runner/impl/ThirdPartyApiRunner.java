package com.cengizhanyavuz.flightsearchapi.runner.impl;

import com.cengizhanyavuz.flightsearchapi.runner.IFlightRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "flight-api.enable-mock-api", havingValue = "false")
public class ThirdPartyApiRunner implements IFlightRunner {
    @Override
    public void start() throws Exception {
        System.out.println("ThirdPartyApiRunner started");
    }
}
