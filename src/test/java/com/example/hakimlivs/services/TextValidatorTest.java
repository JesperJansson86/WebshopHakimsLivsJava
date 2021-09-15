package com.example.hakimlivs.services;

import com.example.hakimlivs.models.DTO.CustomerDTO;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TextValidatorTest {

    @Test
    void validatePassword() {
        assertEquals(true, TextValidator.validatePassword("Password1!"));
        assertEquals(true, TextValidator.validatePassword("1!Password"));
        assertEquals(true, TextValidator.validatePassword("1Password!"));
        assertEquals(true, TextValidator.validatePassword("!Password1"));
        assertEquals(true, TextValidator.validatePassword("Pass!1word"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("password"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("Password"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("Password1"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("1Password"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("!Password"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePassword("pass!1word"));
    }

    @Test
    void validateName() {
        assertEquals(true, TextValidator.validateName("Örjan"));
        assertEquals(true, TextValidator.validateName("örjan"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateName("Örj4n"));
    }

    @Test
    void validatePhone() {
        assertEquals(true, TextValidator.validatePhone("0122222"));
        assertEquals(true, TextValidator.validatePhone("0112222333"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePhone("112"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePhone("11222"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePhone("1122222"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validatePhone("112222a"));
    }

    @Test
    void validateAdress() {
        assertEquals(true, TextValidator.validateAdress("a 23"));
        assertEquals(true, TextValidator.validateAdress("Engata 23"));
        assertEquals(true, TextValidator.validateAdress("Engatautannummer"));
        assertEquals(true, TextValidator.validateAdress("1234546"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAdress("engata 112!"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAdress("superhackerfuntion();"));
    }

    @Test
    void validateAreaCode() {
        assertEquals(true, TextValidator.validateAreaCode("12345"));
        assertEquals(true, TextValidator.validateAreaCode("66666"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAreaCode("02345"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAreaCode("123456"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAreaCode("1234"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateAreaCode("123a5"));
    }

    @Test
    void validateCity() {
        assertEquals(true, TextValidator.validateCity("Stockholm"));
        assertEquals(true, TextValidator.validateCity("By"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateCity("St0ckholm"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateCity("Stockholm1"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateCity("S!ockholm"));
    }

    @Test
    void validateEmail() {
        assertEquals(true, TextValidator.validateEmail("test@test.com"));
        assertEquals(true, TextValidator.validateEmail("test.test.test@test.com"));
        assertEquals(true, TextValidator.validateEmail("t3St@AbrA.se"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("#@com.nu"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("test@com.!"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("test.test@c\"m.nu"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("@com.nu"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("test@com"));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateEmail("test@"));


    }

    @Test
    void validateCustomer() {
        CustomerDTO goodCustomer = new CustomerDTO("firstname", "lastname", "anemail@mail.com", "Password!2", "0812345", "EnGata 1", "11666", "Tokholm");
        CustomerDTO badCustomer = new CustomerDTO("firstname", "lastname", "anemail@mail.com", "password!2", "0812345", "EnGata 1", "11666", "Tokholm");
        assertEquals(true, TextValidator.validateCustomer(goodCustomer));
        assertThrows(IllegalArgumentException.class, () -> TextValidator.validateCustomer(badCustomer));
    }
}