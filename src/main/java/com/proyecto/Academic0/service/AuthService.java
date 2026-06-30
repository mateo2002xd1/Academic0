/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.AuthRequest;
import com.proyecto.Academic0.dto.AuthResponse;
import com.proyecto.Academic0.dto.RefreshTokenRequest;
import com.proyecto.Academic0.dto.RefreshTokenResponse;
import com.proyecto.Academic0.entity.RefreshTokensEntity;
import com.proyecto.Academic0.entity.UsuarioEntity;
import com.proyecto.Academic0.repository.RefreshTokensRepository;
import com.proyecto.Academic0.repository.UsuarioRepository;
import com.proyecto.Academic0.security.JwtService;
import com.proyecto.Academic0.security.PasswordService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
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
    
    @Autowired
    private RefreshTokensRepository refreshTokensRepository;
    
    @Transactional
    public AuthResponse login(AuthRequest usuarioLogin){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(usuarioLogin.getCorreo());
        if(usuarioExiste.isPresent()){
            if(usuarioExiste.get().getPassword_hash() == null){
                throw new RuntimeException("Usuario no registrado");
            }else{
                if(PasswordService.matchPassword(usuarioExiste.get().getPassword_hash(), usuarioLogin.getPassword())){
                    refreshTokensRepository.deleteByUsuario(usuarioExiste.get());
                    String refreshToken = UUID.randomUUID().toString();
                    
                    RefreshTokensEntity refreshTokenRegistro = new RefreshTokensEntity();
                    refreshTokenRegistro.setToken(refreshToken);
                    refreshTokenRegistro.setFechaExpiracion(LocalDateTime.now().plusDays(7));
                    refreshTokenRegistro.setUsuario(usuarioExiste.get());
                    
                    refreshTokensRepository.save(refreshTokenRegistro);
                    
                    return new AuthResponse(jwtService.generarJWT(usuarioExiste.get()), refreshToken);
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
    
    @Transactional
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request){
        Optional<RefreshTokensEntity> refreshTokenExiste = refreshTokensRepository.findByToken(request.getRefreshToken());
        if(refreshTokenExiste.isPresent()){
            if(LocalDateTime.now().isBefore(refreshTokenExiste.get().getFechaExpiracion())){
                    return new RefreshTokenResponse(jwtService.generarJWT(refreshTokenExiste.get().getUsuario()));
            }else{
                System.out.println(refreshTokenExiste.get());
                refreshTokensRepository.delete(refreshTokenExiste.get());
                throw new RuntimeException("Refresh token inválido");
            }
        }else{
            throw new RuntimeException("Refresh token no existe");
        }
    }
}
