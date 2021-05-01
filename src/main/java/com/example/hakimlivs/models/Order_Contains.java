package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Order_Contains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    private Orders order;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Product product;

    private int productAmount;

    public Order_Contains(Orders order, Product product, int productAmount) {
        this.order = order;
        this.product = product;
        this.productAmount = productAmount;
    }
}