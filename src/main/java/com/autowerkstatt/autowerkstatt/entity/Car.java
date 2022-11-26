package com.autowerkstatt.autowerkstatt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin_code")
    private String vin_code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Models models;

}
