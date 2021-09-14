package com.example.hakimlivs.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hakimlivs.models.Customer;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Hanna Edlund
 * Date: 2021-09-07
 * Time: 10:50
 * Project: Java
 */
public class CustomCustomerDetails implements UserDetails{

    private Customer customer;

    public CustomCustomerDetails(Customer customer) {
        super();
        this.customer = customer;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String customerRole = "CUSTOMER";
        String adminRole = "ADMIN";
        String role;

        if(customer.getAdminStatus()){
            role = adminRole;
        }
        else role = customerRole;
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    public String getFirstname(){return customer.getFirstName();}

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
