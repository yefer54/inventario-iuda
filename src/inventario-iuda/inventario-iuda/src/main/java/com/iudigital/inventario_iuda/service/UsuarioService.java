package com.iudigital.inventario_iuda.service;

import com.iudigital.inventario_iuda.dto.UsuarioCreateDto;
import com.iudigital.inventario_iuda.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(UsuarioCreateDto dto);
    List<Usuario> listarTodos();
    Usuario obtenerPorId(Long id);
}
