package com.iudigital.inventario_iuda.repository;


import com.iudigital.inventario_iuda.entity.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface TipoEquipoRepository extends JpaRepository<TipoEquipo, Long> {
    Optional<TipoEquipo> findByNombre(String nombre);
    List<TipoEquipo> findAllByActivoTrue();
}
