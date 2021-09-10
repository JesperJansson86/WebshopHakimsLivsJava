package com.example.hakimlivs.security.jwtToken.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class SecretKeeper {
    public static SecretKeeper INSTANCE;

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    public void init(){
        INSTANCE = this;
    }

    public String getSecretKey(){
        return secretKey;
    }
}
