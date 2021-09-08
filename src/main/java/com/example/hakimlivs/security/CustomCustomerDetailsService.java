package com.example.hakimlivs.security;

import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.repositories.CustomerRepository;
import com.example.hakimlivs.security.CustomCustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Hanna Edlund
 * Date: 2021-09-07
 * Time: 10:50
 * Project: Java
 */
@Service
public class CustomCustomerDetailsService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username);
        if(customer == null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomCustomerDetails(customer);
    }
}
