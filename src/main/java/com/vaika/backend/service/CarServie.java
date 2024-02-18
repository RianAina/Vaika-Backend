package com.vaika.backend.service;

import org.springframework.stereotype.Service;

@Service
public class CarServie {
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
