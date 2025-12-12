package com.iudigital.inventario_iuda.service;


import com.iudigital.inventario_iuda.dto.MarcaDto;
import com.iudigital.inventario_iuda.entity.Marca;

import java.util.List;

public interface MarcaService {
    Marca crear(MarcaDto dto);
    Marca editar(Long id, MarcaDto dto);
    List<Marca> listarTodos();
    Marca obtenerPorId(Long id);
}
