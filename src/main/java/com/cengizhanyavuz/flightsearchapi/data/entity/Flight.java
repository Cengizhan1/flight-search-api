package com.cengizhanyavuz.flightsearchapi.data.entity;

import com.cengizhanyavuz.flightsearchapi.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.time.LocalDateTime;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight  extends BaseEntity {

    @Column(name = "flight_id")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id",referencedColumnName = "airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id",referencedColumnName = "airport_id")
    private Airport arrivalAirport;

    @Column(name = "departure_datetime")
    private LocalDateTime departureDateTime;

    @Column(name = "return_datetime")
    private LocalDateTime returnDateTime;

    @Column(name = "price")
    private double price;
}
