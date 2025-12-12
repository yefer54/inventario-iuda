package com.iudigital.inventario_iuda.service.impl;

import com.iudigital.inventario_iuda.dto.UsuarioCreateDto;
import com.iudigital.inventario_iuda.entity.Role;
import com.iudigital.inventario_iuda.entity.Usuario;
import com.iudigital.inventario_iuda.exception.BadRequestException;
import com.iudigital.inventario_iuda.exception.ResourceNotFoundException;
import com.iudigital.inventario_iuda.repository.UserRepository;
import com.iudigital.inventario_iuda.service.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UserRepository usuarioRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UserRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public Usuario crearUsuario(UsuarioCreateDto dto) {
        usuarioRepo.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new BadRequestException("Email ya registrado");
        });

        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        // encriptar contraseña
        u.setPassword(passwordEncoder.encode(dto.getPassword()));

        // validar rol
        try {
            Role role = Role.valueOf(dto.getRole().toUpperCase());
            u.setRole(role);
        } catch (Exception ex) {
            throw new BadRequestException("Rol inválido. Use ADMIN o DOCENTE");
        }

        u.setActivo(dto.getActivo());
        return usuarioRepo.save(u);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
