/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.controller;

import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.dto.CursoResponse;
import com.proyecto.Academic0.service.CursoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;

/**
 *
 * @author trabajo
 */
@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    
    @PostMapping("")
    public ResponseEntity<String> crearCursoController(@Valid @RequestBody CursoRequest cursoNuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crearCurso(cursoNuevo));
    }
    
    @GetMapping("")
    public ResponseEntity<List<CursoResponse>> listarCursosController(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.listarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarCursoController(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.buscarCurso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCursoController(@PathVariable Integer id, @Valid @RequestBody CursoRequest cursoDatos){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.actualizarCurso(id, cursoDatos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCursoController(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.eliminarCurso(id));
    }
    
}
