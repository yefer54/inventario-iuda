package com.iudigital.inventario_iuda.service.impl;


import com.iudigital.inventario_iuda.dto.EstadoEquipoDto;
import com.iudigital.inventario_iuda.entity.EstadoEquipo;
import com.iudigital.inventario_iuda.exception.BadRequestException;
import com.iudigital.inventario_iuda.exception.ResourceNotFoundException;
import com.iudigital.inventario_iuda.repository.EstadoEquipoRepository;
import com.iudigital.inventario_iuda.service.EstadoEquipoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoEquipoServiceImpl implements EstadoEquipoService {

    private final EstadoEquipoRepository repo;

    public EstadoEquipoServiceImpl(EstadoEquipoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public EstadoEquipo crear(EstadoEquipoDto dto) {
        repo.findByNombre(dto.getNombre()).ifPresent(e -> {
            throw new BadRequestException("Ya existe un estado con ese nombre");
        });
        EstadoEquipo e = new EstadoEquipo();
        e.setNombre(dto.getNombre());
        e.setActivo(dto.getActivo());
        return repo.save(e);
    }

    @Override
    @Transactional
    public EstadoEquipo editar(Long id, EstadoEquipoDto dto) {
        EstadoEquipo e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("EstadoEquipo no encontrado"));
        repo.findByNombre(dto.getNombre()).ifPresent(found -> {
            if (!found.getId().equals(id)) {
                throw new BadRequestException("Otro estado con ese nombre ya existe");
            }
        });
        e.setNombre(dto.getNombre());
        e.setActivo(dto.getActivo());
        return repo.save(e);
    }

    @Override
    public List<EstadoEquipo> listarTodos() {
        return repo.findAll();
    }

    @Override
    public EstadoEquipo obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("EstadoEquipo no encontrado"));
    }
}
