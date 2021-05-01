package com.example.hakimlivs.repositories;
import com.example.hakimlivs.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public Address findAddressByAddress(String a);
    public boolean existsByAddress(String a);
}