package com.example.hakimlivs.security.jwtToken.filter;

import com.example.hakimlivs.models.Customer;
import com.example.hakimlivs.security.CustomCustomerDetails;
import com.example.hakimlivs.security.jwtToken.utility.JWTUtility;
import com.example.hakimlivs.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final JWTUtility jwtUtility;

    private final CustomerService customerService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTUtility jwtUtility, CustomerService customerService) {
        super(authManager);
        this.jwtUtility = jwtUtility;
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);
        }

        LOG.info("Försöker att logga in: {}", token);
        if (token != null) {
            Customer user = customerService.findCustomerByEmail(userName);

            if (user != null) {
                CustomCustomerDetails customCustomerDetails = new CustomCustomerDetails(user);
                return new UsernamePasswordAuthenticationToken(user, null, customCustomerDetails.getAuthorities());
            }
            LOG.info("Kunde inte parsa token och därför inte logga in: {}", token);

            return null;
        }
        LOG.info("Kunde inte hitta token, har du en sådan i din header?");

        return null;
    }
}
