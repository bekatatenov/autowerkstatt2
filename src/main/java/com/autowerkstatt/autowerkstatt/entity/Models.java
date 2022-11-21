package com.autowerkstatt.autowerkstatt.entity;

import com.autowerkstatt.autowerkstatt.enums.Marks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "MODELS")
public class Models {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "marks")
    @Enumerated(EnumType.STRING)
    private Marks marks;
}
