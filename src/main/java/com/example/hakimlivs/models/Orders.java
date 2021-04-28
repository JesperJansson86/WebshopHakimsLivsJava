package com.example.hakimlivs.models;

import com.example.hakimlivs.repositories.OrderStatusRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 14/04/2021
 * Time: 16:25
 * Project: WebshopHakimsLivs
 * Copyright: MIT
 **/
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "deliveryoption_id",referencedColumnName = "id")
    private DeliveryOption deliveryOption;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "adress_id",referencedColumnName = "id")
    private Address deliveryAddress;

    @OneToMany
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
