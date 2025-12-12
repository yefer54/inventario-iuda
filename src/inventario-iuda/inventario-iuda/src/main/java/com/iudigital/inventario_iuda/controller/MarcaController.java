package com.iudigital.inventario_iuda.controller;

import com.iudigital.inventario_iuda.dto.MarcaDto;
import com.iudigital.inventario_iuda.entity.Marca;
import com.iudigital.inventario_iuda.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    public ResponseEntity<List<Marca>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Marca> crear(@Valid @RequestBody MarcaDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Marca> editar(@PathVariable Long id, @Valid @RequestBody MarcaDto dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }
}
