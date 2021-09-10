package com.example.hakimlivs.security;

import com.example.hakimlivs.security.jwtToken.filter.JWTAuthorizationFilter;
import com.example.hakimlivs.security.jwtToken.filter.JwtFilter;
import com.example.hakimlivs.security.jwtToken.utility.JWTUtility;
import com.example.hakimlivs.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final JWTUtility jwtUtility = new JWTUtility();


    @Autowired
    private CustomCustomerDetailsService customCustomerDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

//    @Autowired
//    private JwtFilter jwtFilter;

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

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        UserDetailsService userDetailsService;
//        provider.setUserDetailsService(customCustomerDetailsService);
//        //Tillfällig password encoder class för att kringgå kryptering
//        provider.setPasswordEncoder(new PasswordEnconderTest());
//        return provider;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager(), jwtUtility, customerService);

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/home").hasAuthority("CUSTOMER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/customerinfo").hasAnyAuthority("CUSTOMER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
