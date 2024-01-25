package com.cengizhanyavuz.flightsearchapi.business.dto;

import com.cengizhanyavuz.flightsearchapi.auditing.AuditingAwareBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
public class FlightDTO extends AuditingAwareBaseDto implements Serializable {
    private Long id;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private String departureDateTime;
    private String returnDateTime;
    private double price;
}