/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.AuthRequest;
import com.proyecto.Academic0.dto.AuthResponse;
import com.proyecto.Academic0.entity.UsuarioEntity;
import com.proyecto.Academic0.repository.UsuarioRepository;
import com.proyecto.Academic0.security.JwtService;
import com.proyecto.Academic0.security.PasswordService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordService PasswordService;
    
    @Autowired
    private JwtService jwtService;
    
    public AuthResponse login(AuthRequest usuarioLogin){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(usuarioLogin.getCorreo());
        if(usuarioExiste.isPresent()){
            if(usuarioExiste.get().getPassword_hash() == null){
                throw new RuntimeException("Usuario no registrado");
            }else{
                if(PasswordService.matchPassword(usuarioExiste.get().getPassword_hash(), usuarioLogin.getPassword())){
                    return new AuthResponse(jwtService.generarJWT(usuarioExiste.get().getCorreo()));
                }else{
                    throw new RuntimeException("Credenciales incorrectas");
                }
            }
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }
    
    public String registro(AuthRequest usuarioRegistro){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(usuarioRegistro.getCorreo());
        if(usuarioExiste.isPresent()){
            if(usuarioExiste.get().getPassword_hash() == null){
                UsuarioEntity usuario = usuarioExiste.get();
                usuario.setPassword_hash(PasswordService.encriptarPassword(usuarioRegistro.getPassword()));
                usuarioRepository.save(usuario);
                return "Usuario registrado correctamente";
            }else{
                throw new RuntimeException("Usuario ya registrado");
            }
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }
    
}
