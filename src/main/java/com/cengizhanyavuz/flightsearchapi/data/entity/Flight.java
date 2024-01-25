package com.cengizhanyavuz.flightsearchapi.data.entity;

import com.cengizhanyavuz.flightsearchapi.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight  extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(name = "departure_datetime")
    private String departureDateTime;

    @Column(name = "return_datetime")
    private String returnDateTime;

    @Column(name = "price")
    private double price;
}
