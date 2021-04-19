package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.AreaCode;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
    public Address findAddressByAddress(String a);
}