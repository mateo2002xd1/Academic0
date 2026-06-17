/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.controller;

import com.proyecto.Academic0.dto.AuthRequest;
import com.proyecto.Academic0.dto.AuthResponse;
import com.proyecto.Academic0.security.PasswordService;
import com.proyecto.Academic0.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginController(@Valid @RequestBody AuthRequest credenciales){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(credenciales));
    }
    
    @PutMapping("/registro")
    public ResponseEntity<String> RegistroController(@Valid @RequestBody AuthRequest credenciales){
        return ResponseEntity.status(HttpStatus.OK).body(authService.registro(credenciales));
    }
}
