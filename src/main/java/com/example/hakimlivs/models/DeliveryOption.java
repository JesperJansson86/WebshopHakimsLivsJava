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

    /**
     * typen av delivery
     */
    private String deliveryType;

    /**
     * Konstnaden av att skicka ordern till kunden
     * om kunden hämntar från butiken så är kostnaden 0
     */
    private double deliveryCost;

    public DeliveryOption(String deliveryType, double deliveryCost) {
        this.deliveryType = deliveryType;
        this.deliveryCost = deliveryCost;
    }
}
