/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.repository;

import com.proyecto.Academic0.entity.RefreshTokensEntity;
import com.proyecto.Academic0.entity.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author trabajo
 */
@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokensEntity, Integer>{

    public void deleteByUsuario(UsuarioEntity usuario);

    public Optional<RefreshTokensEntity> findByToken(String refreshToken);
    
}
