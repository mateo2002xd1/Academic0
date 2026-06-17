/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class PasswordService {
    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public String encriptarPassword(String password){
        return encoder.encode(password);
    }
    
    public boolean matchPassword(String passwordHash, String password){
        return encoder.matches(password, passwordHash);
    }
}
