/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.dto;

import jakarta.validation.constraints.Email;
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
public class UsuarioRequest {
    @NotBlank(message = "Nombre no puede ser vacio")
    private String nombre;
    
    @Email(message = "Email no valido")
    private String correo;
    
    @NotNull(message = "Edad no puede ser vacio")
    private Integer edad;
    
    @NotNull(message = "Rol no puede ser vacio")
    private Integer rol;
}