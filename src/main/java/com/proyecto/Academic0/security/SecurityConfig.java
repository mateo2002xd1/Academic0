package com.proyecto.Academic0.security;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // CORS
                .cors(Customizer.withDefaults())

                // CSRF
                .csrf(csrf -> csrf.disable())

                // Manejo de errores de autenticación
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                (request, response, authException) ->
                                        response.sendError(
                                                HttpServletResponse.SC_UNAUTHORIZED,
                                                "No autorizado"
                                        )
                        )
                )

                // Autorización de endpoints
                .authorizeHttpRequests(auth -> auth

                        // CORS Preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Login y registro
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/registro").permitAll()

                        // Usuario
                        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                        .requestMatchers("/usuario/**").authenticated()

                        // Curso
                        .requestMatchers("/curso/**").authenticated()
                        .requestMatchers("/curso").authenticated()

                        // Inscripción
                        .requestMatchers("/inscripcion/**").authenticated()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Resto
                        .anyRequest().permitAll()
                )

                // JWT
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Frontend local + frontend desplegado en Render
        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173",
                        "https://academic0-react.onrender.com"
                )
        );

        // Métodos permitidos
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        // Headers permitidos
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Permitir credenciales
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}