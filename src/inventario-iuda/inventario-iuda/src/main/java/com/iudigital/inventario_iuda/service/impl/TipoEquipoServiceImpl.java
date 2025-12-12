package com.iudigital.inventario_iuda.service.impl;


import com.iudigital.inventario_iuda.dto.TipoEquipoDto;
import com.iudigital.inventario_iuda.entity.TipoEquipo;
import com.iudigital.inventario_iuda.exception.BadRequestException;
import com.iudigital.inventario_iuda.exception.ResourceNotFoundException;
import com.iudigital.inventario_iuda.repository.TipoEquipoRepository;
import com.iudigital.inventario_iuda.service.TipoEquipoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoEquipoServiceImpl implements TipoEquipoService {

    private final TipoEquipoRepository repo;

    public TipoEquipoServiceImpl(TipoEquipoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public TipoEquipo crear(TipoEquipoDto dto) {
        repo.findByNombre(dto.getNombre()).ifPresent(t -> {
            throw new BadRequestException("Ya existe un tipo con ese nombre");
        });
        TipoEquipo t = new TipoEquipo();
        t.setNombre(dto.getNombre());
        t.setActivo(dto.getActivo());
        return repo.save(t);
    }

    @Override
    @Transactional
    public TipoEquipo editar(Long id, TipoEquipoDto dto) {
        TipoEquipo t = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoEquipo no encontrado"));
        // validar si nombre cambia y ya existe otro
        repo.findByNombre(dto.getNombre()).ifPresent(found -> {
            if (!found.getId().equals(id)) {
                throw new BadRequestException("Otro tipo con ese nombre ya existe");
            }
        });
        t.setNombre(dto.getNombre());
        t.setActivo(dto.getActivo());
        return repo.save(t);
    }

    @Override
    public List<TipoEquipo> listarTodos() {
        return repo.findAll();
    }

    @Override
    public TipoEquipo obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoEquipo no encontrado"));
    }
}
