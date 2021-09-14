package com.example.hakimlivs.security;

import com.example.hakimlivs.security.jwtToken.filter.JWTAuthorizationFilter;
import com.example.hakimlivs.security.jwtToken.filter.JWTAuthenticationFilter;
import com.example.hakimlivs.security.jwtToken.filter.SecretKeeper;
import com.example.hakimlivs.security.jwtToken.utility.JWTUtility;
import com.example.hakimlivs.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final JWTUtility jwtUtility = new JWTUtility();


    @Autowired
    private CustomCustomerDetailsService customCustomerDetailsService;

    @Autowired
    private JWTAuthenticationFilter JWTAuthenticationFilter;

    @Autowired
    CustomerService customerService;

    public SecurityConfiguration() throws Exception {
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customCustomerDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder(SecretKeeper.INSTANCE.getPbkSecret(), 1000, 256);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager(), jwtUtility, customerService);

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/*").permitAll()
                .antMatchers("/home").hasAuthority("CUSTOMER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/api/rabbit/*").hasAuthority("ADMIN")
                .antMatchers("/customerinfo").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers("/api/products/all").permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //Authentication Filter (inlogg)
                .addFilter(jwtAuthorizationFilter)                                      //Authorizationg Filter (kontroll av roll)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
