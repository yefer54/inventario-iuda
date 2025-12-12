package com.iudigital.inventario_iuda.repository;



import com.iudigital.inventario_iuda.entity.EstadoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface EstadoEquipoRepository extends JpaRepository<EstadoEquipo, Long> {
    Optional<EstadoEquipo> findByNombre(String nombre);
    List<EstadoEquipo> findAllByActivoTrue();
}
