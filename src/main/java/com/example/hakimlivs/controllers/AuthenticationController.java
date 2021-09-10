package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.security.jwtToken.model.JwtRequest;
import com.example.hakimlivs.security.jwtToken.model.JwtResponse;
import com.example.hakimlivs.security.jwtToken.utility.JWTUtility;
import com.example.hakimlivs.security.CustomCustomerDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomCustomerDetailsService customCustomerDetailsService;

    @GetMapping("/")
    public String home() {
        return "Wow du kom hit madderfakker!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = customCustomerDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }


    @Data
    @AllArgsConstructor
    private class CustomerInfoDTO{
        private String firstname;
        private String lastname;
        private String email;
        private String phonenumber;
        private CustomerAddressDTO address;
    }

    @Data
    @AllArgsConstructor
    private class CustomerAddressDTO{
        private String address;
        private String areacode;
        private String City;
    }

    @GetMapping("/customerinfo")
    public CustomerInfoDTO getUserInfo(@AuthenticationPrincipal Customer customer){
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO(
                customer.getAddress().getAddress() ,
                customer.getAddress().getAreaCode().getAreaCode(),
                customer.getAddress().getAreaCode().getCity().getCity()
        );
        return new CustomerInfoDTO(
                customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getPhoneNumber(),
                customerAddressDTO
        );
    }
}
