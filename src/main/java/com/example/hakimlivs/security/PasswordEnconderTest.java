package com.example.hakimlivs.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Hanna Edlund
 * Date: 2021-09-07
 * Time: 11:44
 * Project: Java
 */
public class PasswordEnconderTest implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
