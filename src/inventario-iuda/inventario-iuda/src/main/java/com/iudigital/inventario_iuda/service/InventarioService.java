package com.iudigital.inventario_iuda.service;


import com.iudigital.inventario_iuda.dto.InventarioDto;
import com.iudigital.inventario_iuda.entity.Inventario;

import java.util.List;

public interface InventarioService {
    Inventario crear(InventarioDto dto);
    Inventario editar(Long id, InventarioDto dto);
    List<Inventario> listarTodos();
    Inventario obtenerPorId(Long id);
}
