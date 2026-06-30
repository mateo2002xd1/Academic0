/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.mapping;

import com.proyecto.Academic0.dto.InscripcionRequest;
import com.proyecto.Academic0.dto.InscripcionResponse;
import com.proyecto.Academic0.entity.InscripcionEntity;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author trabajo
 */
@Mapper(componentModel = "spring")
public interface InscripcionMapper {
    InscripcionResponse toResponse(InscripcionEntity entity);
    
    InscripcionEntity toEntity(InscripcionRequest request);
    
    List<InscripcionResponse> toResponseList(List<InscripcionEntity> entities);
}
