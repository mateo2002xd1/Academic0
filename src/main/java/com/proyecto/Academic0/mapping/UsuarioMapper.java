/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.mapping;

import com.proyecto.Academic0.dto.UsuarioRequest;
import com.proyecto.Academic0.dto.UsuarioResponse;
import com.proyecto.Academic0.entity.UsuarioEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(UsuarioEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "password_hash", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UsuarioEntity toEntity(UsuarioRequest request);

    List<UsuarioResponse> toResponseList(List<UsuarioEntity> entities);
}