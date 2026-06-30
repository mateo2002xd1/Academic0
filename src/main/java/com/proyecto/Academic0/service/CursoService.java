/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.dto.CursoResponse;
import com.proyecto.Academic0.entity.CursoEntity;
import com.proyecto.Academic0.mapping.CursoMapper;
import com.proyecto.Academic0.repository.CursoRepository;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private CursoMapper cursoMapper;
    
    
    public String crearCurso(CursoRequest cursoNuevo){
        CursoEntity cursoIngresar = cursoMapper.toEntity(cursoNuevo);
        cursoIngresar.setFechacreacion(LocalDate.now());
        cursoRepository.save(cursoIngresar);
        return "Curso creado";
    }

    public Page<CursoResponse> listarCursos(Pageable pageable) {

        return cursoRepository.findAll(pageable)
                .map(curso -> cursoMapper.toResponse(curso));
    }

    public CursoResponse buscarCurso(Integer id){
        CursoResponse curso;
        
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        
        if (cursoExiste.isPresent()){
            curso = cursoMapper.toResponse(cursoExiste.get());
            
            return curso;
        }else{
            throw new RuntimeException("Curso no existe");   
        }
    }

    public String actualizarCurso(Integer id, CursoRequest cursoDatos){
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if (cursoExiste.isPresent()){
            CursoEntity cursoActualizar = cursoMapper.toEntity(cursoDatos);
            cursoRepository.save(cursoActualizar);
            
            return "Curso actualizado";   
        }else{
            throw new RuntimeException("Curso no existe");   
        }
    }

    public String eliminarCurso(Integer id){
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if(cursoExiste.isPresent()){
            cursoRepository.deleteById(cursoExiste.get().getId());
            
            return "Curso eliminado";
        }else{
            throw new RuntimeException("Curso no existe");   
        }
        
    }
}