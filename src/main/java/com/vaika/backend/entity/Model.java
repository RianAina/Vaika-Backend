package com.vaika.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private int idModel;

    @Column(name = "model_name", nullable = false, length = 100, unique = true)
    private String modelName;

    @OneToMany(mappedBy = "model")
    private Set<Car> cars;

    @ManyToOne
    @JoinColumn(name = "id_brand" ,nullable = false)
    private Brand brand;
}
