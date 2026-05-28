package com.gym.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/socios")
public class SocioController {

    // 1. Inyectamos el repositorio para conectarnos a la base de datos real
    @Autowired
    private SocioRepository socioRepository;

    // MÉTODO GET: Retorna los socios directamente desde la Base de Datos
    @GetMapping
    public List<Socio> obtenerSocios() {
        return socioRepository.findAll();
    }

    // MÉTODO POST: Guarda al socio en H2 y le genera su ID autoincrementable
    @PostMapping
    public Socio guardarSocio(@RequestBody Socio nuevoSocio) {
        // .save() inserta el registro en la base de datos y le asigna el ID automáticamente
        return socioRepository.save(nuevoSocio);
    }
}