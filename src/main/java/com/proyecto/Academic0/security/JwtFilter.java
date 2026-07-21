/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.security;

import com.proyecto.Academic0.entity.UsuarioEntity;
import com.proyecto.Academic0.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String token = authHeader.substring(7);
        
        try{
            String correo = jwtService.leerCorreo(token);

            Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(correo);

            if(usuarioExiste.isPresent() && jwtService.validarJWT(token, correo)){

                UserDetails usuario = usuarioExiste.get();

                UsernamePasswordAuthenticationToken authAutorizado = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );

                authAutorizado.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authAutorizado);
            }

            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e) {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Token expirado"
                );
            }
        }
    
}
