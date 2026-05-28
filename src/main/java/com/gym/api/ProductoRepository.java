package com.gym.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Al heredar de JpaRepository, Spring Boot te regala automáticamente
    // los métodos para guardar, eliminar, actualizar y buscar productos.
}