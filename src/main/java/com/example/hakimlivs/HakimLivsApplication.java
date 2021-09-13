package com.example.hakimlivs;

import com.example.hakimlivs.security.jwtToken.filter.SecretKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableSwagger2
@SpringBootApplication
public class HakimLivsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HakimLivsApplication.class, args);
    }
}
