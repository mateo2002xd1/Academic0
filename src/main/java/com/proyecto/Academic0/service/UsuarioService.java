/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.UsuarioRequest;
import com.proyecto.Academic0.dto.UsuarioResponse;
import com.proyecto.Academic0.entity.RolEntity;
import com.proyecto.Academic0.entity.UsuarioEntity;
import com.proyecto.Academic0.repository.RolRepository;
import com.proyecto.Academic0.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    public String crearUsuario(UsuarioRequest usuario){
        Optional<RolEntity> usuarioRol = rolRepository.findById(usuario.getRol());
        
        if(usuarioRol.isPresent()){
            RolEntity rol = usuarioRol.get();
        
            UsuarioEntity usuarioNuevo = new UsuarioEntity(null, usuario.getNombre(), usuario.getCorreo(), usuario.getEdad(), null, rol);

            usuarioRepository.save(usuarioNuevo);
        
            return "Usuario creado";
        }else{
            throw new RuntimeException("Rol no existe");
        }
    }

    public List<UsuarioResponse> listarUsuarios(){
        List<UsuarioResponse> usuarios = new ArrayList<>();
        for(UsuarioEntity usuario: usuarioRepository.findAll()){
            UsuarioResponse usuarioBuscado = new UsuarioResponse(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getEdad(), usuario.getRol());
            usuarios.add(usuarioBuscado);
        }
        
        return usuarios;
    }

    public UsuarioResponse buscarUsuario(String correo){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(correo);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioBuscado = usuarioExiste.get();
            UsuarioResponse usuario = new UsuarioResponse(usuarioBuscado.getId(), usuarioBuscado.getNombre(), usuarioBuscado.getCorreo(), usuarioBuscado.getEdad(), usuarioBuscado.getRol());
            return usuario;
        }else{
            throw new RuntimeException("Usuario no existe");
        }
        
        
    }

    public String actualizarUsuario(Integer id, UsuarioRequest usuario){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findById(id);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioBuscado = usuarioExiste.get();
            
            Optional<RolEntity> rolExiste = rolRepository.findById(usuario.getRol());
            UsuarioEntity usuarioActualizar;
            if(rolExiste.isPresent()){
                usuarioActualizar = new UsuarioEntity(usuarioBuscado.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getEdad(), usuarioBuscado.getPassword_hash(), rolExiste.get());   
            }else{
                usuarioActualizar = new UsuarioEntity(usuarioBuscado.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getEdad(), usuarioBuscado.getPassword_hash(), usuarioBuscado.getRol());   
            }
            usuarioRepository.save(usuarioActualizar);
            
            return "Usuario actualizado";
        }else{
            throw new RuntimeException("Usuario no existe");
        }
        
        
    }

    public String eliminarUsuario(Integer id){
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findById(id);
        
        if(usuarioExiste.isPresent()){
            usuarioRepository.delete(usuarioExiste.get());
            
            return "Usuario eliminado";
        }else{
            throw new RuntimeException("Usuario no existe");
        }
    }
}
