package com.autowerkstatt.autowerkstatt.entity;

import com.autowerkstatt.autowerkstatt.Enums.Engine_Type;
import com.autowerkstatt.autowerkstatt.Enums.Marks;
import com.autowerkstatt.autowerkstatt.Enums.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "marks")
    private Marks marks;

    @Enumerated(EnumType.STRING)
    @Column(name = "models")
    private Models models;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private Engine_Type engine_type;

    @Column(name = "vin_code")
    private String vinCode;
}
