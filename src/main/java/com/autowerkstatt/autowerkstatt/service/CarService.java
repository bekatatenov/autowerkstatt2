package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.CarRepository;
import com.autowerkstatt.autowerkstatt.entity.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {
    @Autowired
    private CarRepository repository;

    public void addCar(Car car) {
        this.repository.save(car);
    }

    public Car getByModel(String modelName) {
        return this.repository.findByModels(modelName)
                .orElseThrow(() -> new NoSuchElementException("Не найдена модель по названию: " + modelName));
    }
}
