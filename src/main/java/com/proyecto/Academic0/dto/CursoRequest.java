/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CursoRequest {
    
    @NotBlank(message = "Nombre del curso no puede ser vacio")
    private String nombre;
    
    @NotBlank(message = "Descripcion del curso no puede ser vacio")
    private String descripcion;
    
    @NotBlank(message = "Estado del curso no puede ser vacio")
    private boolean activo;
}
