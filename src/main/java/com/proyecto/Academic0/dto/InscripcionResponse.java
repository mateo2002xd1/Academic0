/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author trabajo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionResponse {
    private Integer id;
    
    @NotNull(message = "Curso no puede ser vacio")
    private String cursoNombre;
    
    @NotNull(message = "Fecha Inscripcion no puede ser vacio")
    private LocalDate fechaInscripcion;
    
    @NotNull(message = "Curso Id no puede ser vacio")
    private Integer cursoId;
}
