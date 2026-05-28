package com.gym.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
    // Este método especial le permitirá a tu app de Flutter buscar socios por correo electrónico
    Optional<Socio> findByCorreo(String correo);
}