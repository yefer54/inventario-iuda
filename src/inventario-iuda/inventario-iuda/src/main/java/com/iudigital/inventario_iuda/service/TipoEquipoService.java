package com.iudigital.inventario_iuda.service;



import com.iudigital.inventario_iuda.dto.TipoEquipoDto;
import com.iudigital.inventario_iuda.entity.TipoEquipo;

import java.util.List;

public interface TipoEquipoService {
    TipoEquipo crear(TipoEquipoDto dto);
    TipoEquipo editar(Long id, TipoEquipoDto dto);
    List<TipoEquipo> listarTodos();
    TipoEquipo obtenerPorId(Long id);
}
