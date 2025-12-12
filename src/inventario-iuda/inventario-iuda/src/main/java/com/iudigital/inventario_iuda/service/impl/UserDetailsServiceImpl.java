package com.iudigital.inventario_iuda.service.impl;

import com.iudigital.inventario_iuda.entity.Usuario;
import com.iudigital.inventario_iuda.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public UserDetailsServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        
        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new UsernameNotFoundException("Usuario inactivo: " + email);
        }

        String roleName = usuario.getRole() != null ? usuario.getRole().name() : "DOCENTE";
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) 
                .authorities(List.of(authority))
                .build();
    }
}
