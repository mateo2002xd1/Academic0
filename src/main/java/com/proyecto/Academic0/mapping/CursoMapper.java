/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.mapping;

import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.dto.CursoResponse;
import com.proyecto.Academic0.entity.CursoEntity;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author trabajo
 */
@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoResponse toResponse(CursoEntity entity);
    
    CursoEntity toEntity(CursoRequest request);
    
    List<CursoResponse> toResponseList(List<CursoEntity> entities);
}
