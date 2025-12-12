package com.iudigital.inventario_iuda.controller;


import com.iudigital.inventario_iuda.dto.EstadoEquipoDto;
import com.iudigital.inventario_iuda.entity.EstadoEquipo;
import com.iudigital.inventario_iuda.service.EstadoEquipoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoEquipoController {

    private final EstadoEquipoService service;

    public EstadoEquipoController(EstadoEquipoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoEquipo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoEquipo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoEquipo> crear(@Valid @RequestBody EstadoEquipoDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoEquipo> editar(@PathVariable Long id, @Valid @RequestBody EstadoEquipoDto dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }
}
