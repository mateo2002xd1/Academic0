/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.repository;

import com.proyecto.Academic0.entity.CursoEntity;
import com.proyecto.Academic0.entity.InscripcionEntity;
import com.proyecto.Academic0.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author trabajo
 */

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEntity, Integer>{

    boolean existsByUsuarioAndCurso(
            UsuarioEntity usuario,
            CursoEntity curso
    );

    public List<InscripcionEntity> findAllByUsuario(UsuarioEntity id);

    public Optional<InscripcionEntity> findByUsuarioAndCurso(UsuarioEntity usuario, CursoEntity curso);
    
}
