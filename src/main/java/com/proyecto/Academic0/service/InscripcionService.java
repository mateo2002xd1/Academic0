/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.dto.InscripcionRequest;
import com.proyecto.Academic0.dto.InscripcionResponse;
import com.proyecto.Academic0.entity.CursoEntity;
import com.proyecto.Academic0.entity.InscripcionEntity;
import com.proyecto.Academic0.entity.UsuarioEntity;
import com.proyecto.Academic0.repository.CursoRepository;
import com.proyecto.Academic0.repository.InscripcionRepository;
import com.proyecto.Academic0.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author trabajo
 */

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    public String crearInscripcion(InscripcionRequest inscripcionNuevo){
        InscripcionEntity inscripcionIngresar = new InscripcionEntity();
        
        String correoUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Optional<UsuarioEntity> usuario = usuarioRepository.findByCorreo(correoUsuario);
        if(usuario.isPresent()){
            inscripcionIngresar.setUsuario(usuario.get());
        }else{
            throw new RuntimeException("Usuario no existe");   
        }
        
        Optional<CursoEntity> curso = cursoRepository.findById(inscripcionNuevo.getCursoId());
        if(curso.isPresent()){
            inscripcionIngresar.setCurso(curso.get());
        }else{
            throw new RuntimeException("Curso no existe");   
        }
        
        if(!inscripcionRepository.existsByUsuarioAndCurso(inscripcionIngresar.getUsuario(), inscripcionIngresar.getCurso())){
            inscripcionIngresar.setFechaInscripcion(LocalDate.now());

            inscripcionRepository.save(inscripcionIngresar);
            return "Incripcion correcta";
        }else{
            throw new RuntimeException("Usuario ya registrado en el curso");   
        }
    }

    public List<InscripcionResponse> listarInscripciones(){
        List<InscripcionResponse> inscripciones = new ArrayList<>();
        
        String correoUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Optional<UsuarioEntity> usuario = usuarioRepository.findByCorreo(correoUsuario);
        if(usuario.isPresent()){
            for(InscripcionEntity inscripcion : inscripcionRepository.findAllByUsuario(usuario.get())){
                InscripcionResponse inscripcionResponse = new InscripcionResponse(inscripcion.getId(), inscripcion.getCurso().getNombre(), inscripcion.getFechaInscripcion());
                inscripciones.add(inscripcionResponse);
            }

            return inscripciones;
        }else{
            throw new RuntimeException("Usuario no existe");   
        }
        
    }

    public String eliminarInscripcion(Integer curso_id){
        String correoUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findByCorreo(correoUsuario);
        
        if(usuarioExiste.isPresent()){
            
            Optional<CursoEntity> cursoExiste = cursoRepository.findById(curso_id);
            if(cursoExiste.isPresent()){
                if(inscripcionRepository.existsByUsuarioAndCurso(usuarioExiste.get(), cursoExiste.get())){
                    Optional<InscripcionEntity> inscripcion = inscripcionRepository.findByUsuarioAndCurso(usuarioExiste.get(), cursoExiste.get());
                    if(inscripcion.isPresent()){
                        inscripcionRepository.delete(inscripcion.get());
                        return "Curso eliminado";    
                    }else{
                        throw new RuntimeException("Inscripcion no existe");   
                    }
                }else{
                    throw new RuntimeException("Usuario no esta inscrito a el curso");   
                }
            }else{
                throw new RuntimeException("Curso no existe");   
            }
        }else{
            throw new RuntimeException("Usuario no existe");   
        }
        
        
        
    }
    
    /*public CursoResponse buscarCurso(Integer id){
        CursoResponse curso = new CursoResponse();
        
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        
        if (cursoExiste.isPresent()){
            curso.setId(cursoExiste.get().getId());
            curso.setNombre(cursoExiste.get().getNombre());
            curso.setDescripcion(cursoExiste.get().getDescripcion());
            curso.setActivo(cursoExiste.get().isActivo());
            curso.setFechacreacion(cursoExiste.get().getFechacreacion());
            
            return curso;
        }else{
            throw new RuntimeException("Curso no existe");   
        }
    }

    public String actualizarCurso(Integer id, CursoRequest cursoDatos){
        Optional<CursoEntity> cursoExiste = cursoRepository.findById(id);
        if (cursoExiste.isPresent()){
            CursoEntity cursoActualizar = new CursoEntity(cursoExiste.get().getId(), cursoDatos.getNombre(), cursoDatos.getDescripcion(), cursoDatos.isActivo(), cursoExiste.get().getFechacreacion());
            cursoRepository.save(cursoActualizar);
            
            return "Curso actualizado";   
        }else{
            throw new RuntimeException("Curso no existe");   
        }
    }

    */
}
