package com.iudigital.inventario_iuda.controller;

import com.iudigital.inventario_iuda.dto.TipoEquipoDto;
import com.iudigital.inventario_iuda.entity.TipoEquipo;
import com.iudigital.inventario_iuda.service.TipoEquipoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoEquipoController {

    private final TipoEquipoService service;

    public TipoEquipoController(TipoEquipoService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    public ResponseEntity<List<TipoEquipo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoEquipo> crear(@Valid @RequestBody TipoEquipoDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoEquipo> editar(@PathVariable Long id, @Valid @RequestBody TipoEquipoDto dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }
}
