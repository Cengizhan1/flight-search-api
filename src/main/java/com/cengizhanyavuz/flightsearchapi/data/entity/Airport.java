package com.cengizhanyavuz.flightsearchapi.data.entity;

import com.cengizhanyavuz.flightsearchapi.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// LOMBOK
@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airports")
public class Airport extends BaseEntity {
    @Column(name = "city")
    private String city;
}