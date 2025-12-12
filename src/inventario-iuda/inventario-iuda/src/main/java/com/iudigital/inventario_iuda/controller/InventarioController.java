package com.iudigital.inventario_iuda.controller;


import com.iudigital.inventario_iuda.dto.InventarioDto;
import com.iudigital.inventario_iuda.entity.Inventario;
import com.iudigital.inventario_iuda.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody InventarioDto dto) {
        Inventario creado = service.crear(dto);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> editar(@PathVariable Long id, @Valid @RequestBody InventarioDto dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }
}
