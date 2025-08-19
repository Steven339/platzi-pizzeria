package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Long idItem;

    @Column(name = "id_pizza", nullable = false)
    private Long idPizza;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id", insertable = false, updatable = false)
    private PizzaEntity pizza;

}
