package com.vaika.backend.controller;

import com.vaika.backend.entity.Car;
import com.vaika.backend.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestBody Car car) {
        try {
            carService.createCar(car);
            return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
