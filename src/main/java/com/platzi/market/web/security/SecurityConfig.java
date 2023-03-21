package com.platzi.market.web.security;


import com.platzi.market.domain.service.PlatziUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private PlatziUserDetailsService projectUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(projectUserDetailsService)
                .and()
                .build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}