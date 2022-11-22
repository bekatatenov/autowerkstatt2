package com.autowerkstatt.autowerkstatt.entity;

import com.autowerkstatt.autowerkstatt.enums.Faults;
import com.autowerkstatt.autowerkstatt.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_creation")
    private Date dateTime;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_before")
    private Date dateBefore;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Faults faults;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
