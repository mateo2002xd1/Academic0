/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.dto.CursoResponse;
import com.proyecto.Academic0.entity.CursoEntity;
import com.proyecto.Academic0.repository.CursoRepository;
import java.time.LocalDate;
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
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    public String crearCurso(CursoRequest cursoNuevo){
        CursoEntity cursoIngresar = new CursoEntity();
        
        cursoIngresar.setNombre(cursoNuevo.getNombre());
        cursoIngresar.setDescripcion(cursoNuevo.getDescripcion());
        cursoIngresar.setActivo(cursoNuevo.isActivo());
        cursoIngresar.setFechacreacion(LocalDate.now());
        
        cursoRepository.save(cursoIngresar);
        return "Curso creado";
    }

    public List<CursoResponse> listarCursos(){
        List<CursoResponse> cursos = new ArrayList<>();
        for(CursoEntity curso : cursoRepository.findAll()){
            CursoResponse cursoResponse = new CursoResponse(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.isActivo(), curso.getFechacreacion());
            cursos.add(cursoResponse);
        }
        
        return cursos;
    }

    public CursoResponse buscarCurso(Integer id){
        CursoResponse curso = new CursoResponse();
        
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if (cursoExiste.isPresent()){
            curso.setId(cursoExiste.get().getId());
            curso.setNombre(cursoExiste.get().getNombre());
            curso.setDescripcion(cursoExiste.get().getDescripcion());
            curso.setActivo(cursoExiste.get().isActivo());
            curso.setFechacreacion(cursoExiste.get().getFechacreacion());
        }
        
        return curso;
    }

    public String actualizarCurso(Integer id, CursoRequest cursoDatos){
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if (cursoExiste.isPresent()){
            CursoEntity cursoActualizar = new CursoEntity(cursoExiste.get().getId(), cursoDatos.getNombre(), cursoDatos.getDescripcion(), cursoDatos.isActivo(), cursoExiste.get().getFechacreacion());
            cursoRepository.save(cursoActualizar);
            
            return "Curso actualizado";   
        }else{
            return "Curso no existe";   
        }
    }

    public String eliminarCurso(Integer id){
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if(cursoExiste.isPresent()){
            cursoRepository.deleteById(cursoExiste.get().getId());
            
            return "Curso eliminado";
        }else{
            return "Curso no existe";  
        }
        
    }
}