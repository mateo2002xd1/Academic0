/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.security;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private JwtFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
    .csrf(csrf -> csrf.disable())
    .exceptionHandling(ex -> ex
        .authenticationEntryPoint(
            (request, response, authException) ->
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "No autorizado"
                )
        )
    )
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/login").permitAll()
        .requestMatchers("/auth/registro").permitAll()
        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
        .requestMatchers("/usuario/**").authenticated()
        .requestMatchers("/curso/**").authenticated()
        .requestMatchers("/curso**").authenticated()
        .requestMatchers("/inscripcion/**").authenticated()
        .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
        .anyRequest().permitAll()
    )
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}