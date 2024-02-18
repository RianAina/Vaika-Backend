package com.vaika.backend.service;

import com.vaika.backend.entity.Car;
import com.vaika.backend.entity.Color;
import com.vaika.backend.entity.Model;
import com.vaika.backend.entity.Order;
import com.vaika.backend.repository.CarRepository;
import com.vaika.backend.repository.ColorRepository;
import com.vaika.backend.repository.ModelRepository;
import com.vaika.backend.repository.OrderRepository;
import jakarta.transaction.Transactional;
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

    public void createCar(Car car) {
        // Vérifiez si l'ID de la voiture est nul ou existe déjà dans la base de données
        if (car.getIdCar() != 0 || carRepository.existsById(car.getIdCar())) {
            throw new RuntimeException("Car ID must be null or unique");
        }

        // Vérifiez et définissez la relation avec l'ordre
        Order order = car.getOrder();
        if (order == null || order.getIdOrder() == 0 || !orderRepository.existsById(order.getIdOrder())) {
            throw new RuntimeException("Invalid or non-existing order for the car");
        }
        car.setOrder(order);

        // Vérifiez et définissez la relation avec le modèle
        Model model = car.getModel();
        if (model == null || model.getIdModel() == 0 || !modelRepository.existsById(model.getIdModel())) {
            throw new RuntimeException("Invalid or non-existing model for the car");
        }
        car.setModel(model);

        // Vérifiez et définissez la relation avec la couleur
        Color color = car.getColor();
        if (color == null || color.getIdColor() == 0 || !colorRepository.existsById(color.getIdColor())) {
            throw new RuntimeException("Invalid or non-existing color for the car");
        }
        car.setColor(color);

        // Effectuez la création de la voiture dans la base de données
        carRepository.save(car);
    }
}
