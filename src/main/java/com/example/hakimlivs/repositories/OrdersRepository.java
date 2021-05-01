package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.OrderStatus;
import com.example.hakimlivs.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    public boolean existsById(Long id);
    public Iterable<Orders> findOrdersByOrderStatus(OrderStatus id);
}
