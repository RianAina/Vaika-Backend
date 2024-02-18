package com.vaika.backend.service;

import com.vaika.backend.entity.Car;
import com.vaika.backend.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public void createCar(Car car) {
        carRepository.save(car);
    }
}
