/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String mi_clave_super_protegida;
    
    @Value("${jwt.expiration}")
    private long expiracionToken;
    
    public String generarJWT(String correo){
        return Jwts.builder().
                subject(correo).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + expiracionToken)).
                signWith(generarKey()).
                compact();
        
    }
    
    public boolean validarJWT(String token, String correo){
        String usuarioToken = leerCorreo(token);
        
        return usuarioToken.equals(correo) && !tokenExpirado(token);
    }
    
    public boolean tokenExpirado(String token){
        Claims claims = leerClaims(token);
        
        return claims.getExpiration().before(new Date());
    }
    
    public String leerCorreo(String token){
        Claims claims = leerClaims(token);
        return claims.getSubject();
    }
    
    public Claims leerClaims(String token){
                return Jwts.parser()
                .verifyWith(generarKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public SecretKey generarKey(){
        return Keys.hmacShaKeyFor(mi_clave_super_protegida.getBytes());
    }
}