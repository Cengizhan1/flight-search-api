package com.cengizhanyavuz.flightsearchapi.job;

import com.cengizhanyavuz.flightsearchapi.runner.IFlightRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FlightApiJob {
    private final IFlightRunner flightRunner;
    @Scheduled(fixedRate = 200000L)
    public void start() throws Exception {
        flightRunner.start();
    }
}
