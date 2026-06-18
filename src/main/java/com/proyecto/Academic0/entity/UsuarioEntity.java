/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author trabajo
 */
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "password_hash")
    private String password_hash;
    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolEntity rol;
    
    @Override
    public String getUsername(){
        return correo;
    }
    
    @Override
    public String getPassword(){
        return password_hash;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(
            new SimpleGrantedAuthority(rol.getNombre())
        );
    }
    
}
