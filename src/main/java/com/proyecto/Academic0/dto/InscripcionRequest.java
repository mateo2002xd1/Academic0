/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.dto;

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
public class InscripcionRequest {
    @NotNull(message = "Id del curso necesario para inscribirse al curso")
    private Integer cursoId;
}
