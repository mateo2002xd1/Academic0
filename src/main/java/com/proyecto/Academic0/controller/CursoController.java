/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.controller;

import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.dto.CursoResponse;
import com.proyecto.Academic0.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trabajo
 */
@Tag(name = "Cursos", description = "Portal de cursos")
@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    
    @Operation(
        summary = "Crear cursos",
        description = "Crear nuevos cursos"
    )
    @ApiResponse(responseCode = "200", description = "Curso creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> crearCursoController(@Valid @RequestBody CursoRequest cursoNuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crearCurso(cursoNuevo));
    }
    
    @Operation(
        summary = "Listar cursos",
        description = "Listar todos los cursos disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Cursos listados correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("")
    public Page<CursoResponse> listarCursosController(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
    
        return cursoService.listarCursos(pageable);
    }
    
    @Operation(
        summary = "Buscar curso",
        description = "Buscar un curso en concreto"
    )
    @ApiResponse(responseCode = "200", description = "Cursos listado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarCursoController(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.buscarCurso(id));
    }

    @Operation(
        summary = "Actualizar curso",
        description = "Actualizar un curso en concreto"
    )
    @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> actualizarCursoController(@PathVariable Integer id, @Valid @RequestBody CursoRequest cursoDatos){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.actualizarCurso(id, cursoDatos));
    }

    @Operation(
        summary = "Eliminar curso",
        description = "Eliminar un curso en concreto"
    )
    @ApiResponse(responseCode = "200", description = "Curso elimindo correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarCursoController(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.eliminarCurso(id));
    }
    
}
