package com.vaika.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Color")
public class Color implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_color")
    private int idColor;

    @Column(name = "color_name")
    private String colorName;

    @OneToMany(mappedBy = "color")
    private Set<Car> cars;
}
