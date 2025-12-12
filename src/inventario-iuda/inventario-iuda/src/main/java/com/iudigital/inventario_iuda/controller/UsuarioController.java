package com.iudigital.inventario_iuda.controller;

import com.iudigital.inventario_iuda.dto.UsuarioCreateDto;
import com.iudigital.inventario_iuda.entity.Usuario;
import com.iudigital.inventario_iuda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioCreateDto dto) {
        Usuario u = service.crearUsuario(dto);
        u.setPassword(null);
        return ResponseEntity.ok(u);
    }
}
