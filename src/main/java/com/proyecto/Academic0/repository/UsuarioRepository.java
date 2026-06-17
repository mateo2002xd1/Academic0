/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.repository;

import com.proyecto.Academic0.entity.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author trabajo
 */

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    public Optional<UsuarioEntity> findByNombre(String nombre);

    public Optional<UsuarioEntity> findByCorreo(String correo);

    
}
