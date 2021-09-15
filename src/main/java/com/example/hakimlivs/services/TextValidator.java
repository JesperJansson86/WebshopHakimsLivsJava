package com.example.hakimlivs.services;

import com.example.hakimlivs.models.DTO.CustomerDTO;

import java.util.regex.Pattern;

public class TextValidator {
    public static boolean validatePassword(String password) {
        final String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$";
        if(Pattern.matches(regex,password)){
            return true;
        } else {
            throw new IllegalArgumentException("Password Validation Failed");
        }
    }

    public static boolean validateName(String name){
        final String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]{2,30}$";
        if(Pattern.matches(regex,name)){
            return true;
        } else {
            throw new IllegalArgumentException("Name Validation Failed");
        }
    }

    public static boolean validatePhone(String phone){
        final String regex = "^0\\d{6,}";
        if(Pattern.matches(regex,phone)){
            return true;
        } else {
            throw new IllegalArgumentException("Phone Validation Failed");
        }
    }

    public static boolean validateAdress(String adress){
        final String regex = "^[a-z A-ZåöäÅÖÄ 0-9]{1,30}$";
        if(Pattern.matches(regex,adress)){
            return true;
        } else {
            throw new IllegalArgumentException("Adress Validation Failed");
        }
    }

    public static boolean validateAreaCode(String areaCode){
        final String regex = "^[1-9]\\d{4}";
        if(Pattern.matches(regex,areaCode)){
            return true;
        } else {
            throw new IllegalArgumentException("Areacode Validation Failed");
        }
    }

    public static boolean validateCity(String city){
        final String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]{2,30}$";
        if(Pattern.matches(regex,city)){
            return true;
        } else {
            throw new IllegalArgumentException("City Validation Failed");
        }
    }

    public static boolean validateEmail(String email){
        final String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if(Pattern.matches(regex,email)){
            return true;
        } else {
            throw new IllegalArgumentException("Email Validation Failed");
        }
    }

    public static boolean validateCustomer(CustomerDTO customerDTO){
        return validatePassword(customerDTO.getPassword()) &&
                validateName(customerDTO.getFirstname()) &&
                validateName(customerDTO.getLastname()) &&
                validatePhone(customerDTO.getPhone()) &&
                validateAdress(customerDTO.getAddress()) &&
                validateAreaCode(customerDTO.getAreaCode()) &&
                validateCity(customerDTO.getCity()) &&
                validateEmail((customerDTO.getEmail()));
    }
}
