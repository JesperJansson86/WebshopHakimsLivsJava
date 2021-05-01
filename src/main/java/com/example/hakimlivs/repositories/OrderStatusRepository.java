package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

    OrderStatus findStatusByOrderStatus(String orderStatus);
}
