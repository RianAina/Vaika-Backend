package com.vaika.backend.service;

import com.vaika.backend.entity.*;
import com.vaika.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private BrandRepository brandRepository;

    public void createCar(Car car) {
        // Vérifiez si l'ID de la voiture est nul ou existe déjà dans la base de données
        if (car.getIdCar() != 0 || carRepository.existsById(car.getIdCar())) {
            throw new RuntimeException("Car ID must be null or unique");
        }

        // Vérifiez et créez automatiquement le modèle s'il n'existe pas
        Model model = car.getModel();
        if (model.getModelName() == null) {
            throw new RuntimeException("Invalid model for the car");
        }

        if (!modelRepository.existsById(model.getIdModel())) {
            modelRepository.save(model);
            // Vérifiez et créez automatiquement la marque (Brand) s'il n'existe pas
            Brand brand = model.getBrand();
            if (brand == null) {
                throw new RuntimeException("Invalid brand for the car");
            }

            if (!brandRepository.existsById(brand.getIdBrand())) {
                brandRepository.save(brand);
            }
        }
        car.setModel(model);

        // Vérifiez et créez automatiquement la couleur s'il n'existe pas
//        Color color = car.getColor();
//        if (color == null || color.getIdColor() == 0) {
//            throw new RuntimeException("Invalid color for the car");
//        }
//
//        if (!colorRepository.existsById(color.getIdColor())) {
//            colorRepository.save(color);
//        }
//        car.setColor(color);

        // Effectuez la création de la voiture dans la base de données
        carRepository.save(car);
    }
}
