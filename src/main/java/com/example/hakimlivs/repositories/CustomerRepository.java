package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findCustomerByFirstName();
}