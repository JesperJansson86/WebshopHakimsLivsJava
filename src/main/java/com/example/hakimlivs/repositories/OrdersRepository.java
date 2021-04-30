package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.OrderStatus;
import com.example.hakimlivs.models.Orders;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 09:59
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    public boolean existsById (Long id);
    public Iterable<Orders> findOrdersByOrderStatus (OrderStatus id);
}
