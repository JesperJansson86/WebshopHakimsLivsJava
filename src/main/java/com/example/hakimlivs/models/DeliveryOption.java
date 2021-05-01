package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class DeliveryOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String deliveryType;

    private double deliveryCost;

    public DeliveryOption(String deliveryType, double deliveryCost) {
        this.deliveryType = deliveryType;
        this.deliveryCost = deliveryCost;
    }
}
