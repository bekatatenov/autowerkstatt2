package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.CarRepository;
import com.autowerkstatt.autowerkstatt.entity.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void carSave(Car car) {
        carRepository.save(car);
    }

}
