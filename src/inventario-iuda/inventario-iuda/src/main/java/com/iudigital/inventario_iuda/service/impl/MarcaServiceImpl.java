package com.iudigital.inventario_iuda.service.impl;



import com.iudigital.inventario_iuda.dto.MarcaDto;
import com.iudigital.inventario_iuda.entity.Marca;
import com.iudigital.inventario_iuda.exception.BadRequestException;
import com.iudigital.inventario_iuda.exception.ResourceNotFoundException;
import com.iudigital.inventario_iuda.repository.MarcaRepository;
import com.iudigital.inventario_iuda.service.MarcaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository repo;

    public MarcaServiceImpl(MarcaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Marca crear(MarcaDto dto) {
        repo.findByNombre(dto.getNombre()).ifPresent(m -> {
            throw new BadRequestException("Ya existe una marca con ese nombre");
        });
        Marca m = new Marca();
        m.setNombre(dto.getNombre());
        m.setActivo(dto.getActivo());
        return repo.save(m);
    }

    @Override
    @Transactional
    public Marca editar(Long id, MarcaDto dto) {
        Marca m = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));
        repo.findByNombre(dto.getNombre()).ifPresent(found -> {
            if (!found.getId().equals(id)) {
                throw new BadRequestException("Otra marca con ese nombre ya existe");
            }
        });
        m.setNombre(dto.getNombre());
        m.setActivo(dto.getActivo());
        return repo.save(m);
    }

    @Override
    public List<Marca> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Marca obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));
    }
}
