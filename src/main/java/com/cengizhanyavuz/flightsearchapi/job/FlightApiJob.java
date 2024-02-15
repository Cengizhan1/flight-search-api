package com.cengizhanyavuz.flightsearchapi.job;

import com.cengizhanyavuz.flightsearchapi.business.service.IFlightRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FlightApiJob {
    private final IFlightRunner flightRunner;
    @Scheduled(fixedRate = 24 * 60*30)
    public void start() throws Exception {
        System.out.println("Starting Job");
        flightRunner.start();
    }
}
