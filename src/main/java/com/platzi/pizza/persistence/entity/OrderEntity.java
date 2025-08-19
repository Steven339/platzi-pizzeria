package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "id_customer", nullable = false)
    private String idCustomer;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String method;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @OneToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> items;
}
