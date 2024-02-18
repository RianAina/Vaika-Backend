package com.vaika.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private int idCar;

    @Column(name = "price")
    private String price;

    @Column(name = "km")
    private int km;

    @Column(name = "construction_date")
    private Date constructionDate;

    @Column(name = "date_of_sale")
    private Date dateOfSale;

    @Column(name = "is_sold")
    private Boolean isSold;

    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_model", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;
}
