package com.iudigital.inventario_iuda.controller;

import com.iudigital.inventario_iuda.dto.LoginRequestDto;
import com.iudigital.inventario_iuda.dto.LoginResponseDto;
import com.iudigital.inventario_iuda.dto.UsuarioCreateDto;
import com.iudigital.inventario_iuda.entity.Usuario;
import com.iudigital.inventario_iuda.repository.UserRepository;
import com.iudigital.inventario_iuda.security.JwtUtil;
import com.iudigital.inventario_iuda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final UserRepository usuarioRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, UserRepository usuarioRepo, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.usuarioRepo = usuarioRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // Registro: sólo ADMIN debería crear usuarios normalmente.
    // Aquí se deja público para facilitar pruebas, pero puedes proteger con @PreAuthorize("hasRole('ADMIN')") y mover creación a UsuarioController.
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsuarioCreateDto dto) {
        Usuario u = usuarioService.crearUsuario(dto);
        // devolver sin password
        u.setPassword(null);
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto dto) {
        return usuarioRepo.findByEmail(dto.getEmail())
                .map(u -> {
                    if (!u.getActivo()) {
                        return ResponseEntity.badRequest().body("Usuario inactivo");
                    }
                    if (passwordEncoder.matches(dto.getPassword(), u.getPassword())) {
                        String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
                        LoginResponseDto resp = new LoginResponseDto(token, u.getRole().name(), u.getNombre(), u.getEmail());
                        return ResponseEntity.ok(resp);
                    } else {
                        return ResponseEntity.status(401).body("Credenciales inválidas");
                    }
                }).orElseGet(() -> ResponseEntity.status(401).body("Credenciales inválidas"));
    }
}
