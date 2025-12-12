package com.iudigital.inventario_iuda.service.impl;


import com.iudigital.inventario_iuda.dto.InventarioDto;
import com.iudigital.inventario_iuda.entity.*;
import com.iudigital.inventario_iuda.exception.BadRequestException;
import com.iudigital.inventario_iuda.exception.ResourceNotFoundException;
import com.iudigital.inventario_iuda.repository.*;
import com.iudigital.inventario_iuda.service.InventarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepo;
    private final UserRepository usuarioRepo;
    private final MarcaRepository marcaRepo;
    private final EstadoEquipoRepository estadoRepo;
    private final TipoEquipoRepository tipoRepo;

    public InventarioServiceImpl(InventarioRepository inventarioRepo,
                                 UserRepository usuarioRepo,
                                 MarcaRepository marcaRepo,
                                 EstadoEquipoRepository estadoRepo,
                                 TipoEquipoRepository tipoRepo) {
        this.inventarioRepo = inventarioRepo;
        this.usuarioRepo = usuarioRepo;
        this.marcaRepo = marcaRepo;
        this.estadoRepo = estadoRepo;
        this.tipoRepo = tipoRepo;
    }

    @Override
    @Transactional
    public Inventario crear(InventarioDto dto) {
        // unicidad
        inventarioRepo.findBySerial(dto.getSerial()).ifPresent(i -> {
            throw new BadRequestException("Serial ya registrado");
        });
        inventarioRepo.findByModelo(dto.getModelo()).ifPresent(i -> {
            throw new BadRequestException("Modelo ya registrado");
        });

        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId()).orElseThrow(() -> new BadRequestException("Usuario no encontrado"));
        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new BadRequestException("Usuario no está activo");
        }

        Marca marca = marcaRepo.findById(dto.getMarcaId()).orElseThrow(() -> new BadRequestException("Marca no encontrada"));
        if (!Boolean.TRUE.equals(marca.getActivo())) {
            throw new BadRequestException("Marca no está activa");
        }

        EstadoEquipo estado = estadoRepo.findById(dto.getEstadoEquipoId()).orElseThrow(() -> new BadRequestException("Estado equipo no encontrado"));
        TipoEquipo tipo = tipoRepo.findById(dto.getTipoEquipoId()).orElseThrow(() -> new BadRequestException("Tipo equipo no encontrado"));

        Inventario inv = new Inventario();
        inv.setSerial(dto.getSerial());
        inv.setModelo(dto.getModelo());
        inv.setDescripcion(dto.getDescripcion());
        inv.setColor(dto.getColor());
        inv.setFechaCompra(dto.getFechaCompra());
        inv.setPrecio(dto.getPrecio());
        inv.setUsuario(usuario);
        inv.setMarca(marca);
        inv.setEstadoEquipo(estado);
        inv.setTipoEquipo(tipo);

        return inventarioRepo.save(inv);
    }

    @Override
    @Transactional
    public Inventario editar(Long id, InventarioDto dto) {
        Inventario inv = inventarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));

        inventarioRepo.findBySerial(dto.getSerial()).ifPresent(found -> {
            if (!found.getId().equals(inv.getId())) throw new BadRequestException("Serial ya registrado por otro equipo");
        });
        inventarioRepo.findByModelo(dto.getModelo()).ifPresent(found -> {
            if (!found.getId().equals(inv.getId())) throw new BadRequestException("Modelo ya registrado por otro equipo");
        });

        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId()).orElseThrow(() -> new BadRequestException("Usuario no encontrado"));
        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new BadRequestException("Usuario no está activo");
        }

        Marca marca = marcaRepo.findById(dto.getMarcaId()).orElseThrow(() -> new BadRequestException("Marca no encontrada"));
        if (!Boolean.TRUE.equals(marca.getActivo())) {
            throw new BadRequestException("Marca no está activa");
        }

        EstadoEquipo estado = estadoRepo.findById(dto.getEstadoEquipoId()).orElseThrow(() -> new BadRequestException("Estado equipo no encontrado"));
        TipoEquipo tipo = tipoRepo.findById(dto.getTipoEquipoId()).orElseThrow(() -> new BadRequestException("Tipo equipo no encontrado"));

        inv.setSerial(dto.getSerial());
        inv.setModelo(dto.getModelo());
        inv.setDescripcion(dto.getDescripcion());
        inv.setColor(dto.getColor());
        inv.setFechaCompra(dto.getFechaCompra());
        inv.setPrecio(dto.getPrecio());
        inv.setUsuario(usuario);
        inv.setMarca(marca);
        inv.setEstadoEquipo(estado);
        inv.setTipoEquipo(tipo);

        return inventarioRepo.save(inv);
    }

    @Override
    public List<Inventario> listarTodos() {
        return inventarioRepo.findAll();
    }

    @Override
    public Inventario obtenerPorId(Long id) {
        return inventarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
    }
}
