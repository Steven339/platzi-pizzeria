package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private Double price;

    @Column
    private Boolean vegetarian;

    @Column
    private Boolean vegan;

    @Column(nullable = false)
    private Boolean available;
}
