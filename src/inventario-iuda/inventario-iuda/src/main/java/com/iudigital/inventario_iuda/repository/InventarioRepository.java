package com.iudigital.inventario_iuda.repository;

import com.iudigital.inventario_iuda.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findBySerial(String serial);
    Optional<Inventario> findByModelo(String modelo);
}
