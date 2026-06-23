/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.controller;

import com.proyecto.Academic0.dto.InscripcionRequest;
import com.proyecto.Academic0.dto.InscripcionResponse;
import com.proyecto.Academic0.service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trabajo
 */
@Tag(name = "Incripciones")
@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;
    
    @Operation(
        summary = "Inscribir a curso",
        description = "Inscribir a un curso en concreto"
    )
    @ApiResponse(responseCode = "200", description = "Inscripcion correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> crearInscripcionController(@Valid @RequestBody InscripcionRequest inscripcion){
        return ResponseEntity.status(HttpStatus.CREATED).body(inscripcionService.crearInscripcion(inscripcion));
    }
    
    @Operation(
        summary = "Listar las incripciones",
        description = "Listar todas las incripciones del usuario"
    )
    @ApiResponse(responseCode = "200", description = "Inscripciones actuales")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<InscripcionResponse>> listarInscripcionesController(){
        return ResponseEntity.status(HttpStatus.OK).body(inscripcionService.listarInscripciones());
    }
    
    @Operation(
        summary = "Eliminar incripciones",
        description = "Elimninar incripciones"
    )
    @ApiResponse(responseCode = "200", description = "Inscripcion eliminada correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> eliminarInscripcionController(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(inscripcionService.eliminarInscripcion(id));
    }
}
