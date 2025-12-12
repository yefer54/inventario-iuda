package com.iudigital.inventario_iuda.repository;


import com.iudigital.inventario_iuda.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNombre(String nombre);
    List<Marca> findAllByActivoTrue();
}
