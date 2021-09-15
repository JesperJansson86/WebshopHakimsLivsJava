package com.example.hakimlivs.models.DTO;

import com.example.hakimlivs.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String areaCode;
    private String city;



    public boolean compareToCustomer(Customer customer){
        return (
                customer.getFirstName().equalsIgnoreCase(firstname) &&
                customer.getLastName().equalsIgnoreCase(lastname) &&
                customer.getEmail().equalsIgnoreCase(email) &&
                customer.getPhoneNumber().equalsIgnoreCase(phone) &&
                customer.getAddress().getAddress().equalsIgnoreCase(address) &&
                customer.getAddress().getAreaCode().getAreaCode().equalsIgnoreCase(areaCode) &&
                customer.getAddress().getAreaCode().getCity().getCity().equalsIgnoreCase(city)
                );
    }
}
