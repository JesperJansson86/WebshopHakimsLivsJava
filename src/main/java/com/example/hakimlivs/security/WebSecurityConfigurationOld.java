//package com.example.hakimlivs.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.RabbitMqConfiguration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
///**
// * Created by Hanna Edlund
// * Date: 2021-09-07
// * Time: 10:49
// * Project: Java
// */
//@RabbitMqConfiguration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider
//                = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        //Tillfällig password encoder class för att kringgå kryptering
//        provider.setPasswordEncoder(new PasswordEnconderTest());
//        return provider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/*").permitAll()
//                .antMatchers("/home").hasAuthority("CUSTOMER")
//                .antMatchers("/admin").hasAuthority("ADMIN")
////                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic()
//                .and()
//                .logout().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/admin");
//    }
//}
