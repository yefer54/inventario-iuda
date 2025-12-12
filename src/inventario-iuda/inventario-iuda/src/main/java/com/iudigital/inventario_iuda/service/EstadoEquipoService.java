package com.iudigital.inventario_iuda.service;


import com.iudigital.inventario_iuda.dto.EstadoEquipoDto;
import com.iudigital.inventario_iuda.entity.EstadoEquipo;

import java.util.List;

public interface EstadoEquipoService {
    EstadoEquipo crear(EstadoEquipoDto dto);
    EstadoEquipo editar(Long id, EstadoEquipoDto dto);
    List<EstadoEquipo> listarTodos();
    EstadoEquipo obtenerPorId(Long id);
}
