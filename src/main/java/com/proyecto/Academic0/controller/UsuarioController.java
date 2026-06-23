package com.proyecto.Academic0.controller;

import com.proyecto.Academic0.dto.UsuarioRequest;
import com.proyecto.Academic0.dto.UsuarioResponse;
import com.proyecto.Academic0.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuario", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Crear usuario",
        description = "Registra un nuevo usuario en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("")
    public ResponseEntity<String> crearUsuarioController(@Valid @RequestBody UsuarioRequest usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.crearUsuario(usuario));
    }

    @Operation(
        summary = "Listar usuarios",
        description = "Obtiene la lista de todos los usuarios (solo ADMIN)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.listarUsuarios());
    }

    @Operation(
        summary = "Buscar usuario por correo",
        description = "Retorna un usuario específico usando su correo electrónico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping("/{correo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable String correo) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.buscarUsuario(correo));
    }

    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza la información de un usuario por ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "403", description = "No autorizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequest usuario) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.actualizarUsuario(id, usuario));
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema por ID (solo ADMIN)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "403", description = "No autorizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.eliminarUsuario(id));
    }
}