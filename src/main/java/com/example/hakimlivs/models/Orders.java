package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    @ManyToOne
    private OrderStatus orderStatus;
    @ManyToOne
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "deliveryoption_id", referencedColumnName = "id")
    private DeliveryOption deliveryOption;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    private Address deliveryAddress;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "order_id")
    private List<Order_Contains> productList;

    public Orders(LocalDate orderDate, OrderStatus orderStatus, Customer customer, DeliveryOption deliveryOption, Address deliveryAddress) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
    }
}
