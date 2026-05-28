package com.gym.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "*") // Permite que Flutter se conecte sin bloqueos de red
public class CheckInController {

    @Autowired
    private SocioRepository socioRepository;

    @PostMapping("/checkin")
    public ResponseEntity<Map<String, Object>> registrarCheckIn(@RequestBody Map<String, String> request) {
        // 💬 Esto imprimirá en la terminal de VS Code lo que mande el celular al escanear
        System.out.println("====== DATOS RECIBIDOS DESDE FLUTTER ======");
        System.out.println(request);
        System.out.println("===========================================");

        // 🧠 Extracción inteligente del correo/datos para evitar valores nulos
        String correo = request.get("correo");
        
        if (correo == null) {
            correo = request.get("email"); // Por si en Flutter se llama 'email'
        }
        if (correo == null) {
            correo = request.get("qrData"); // Por si en Flutter se llama 'qrData'
        }
        // Si Flutter manda el texto plano directo dentro del JSON, agarra el primer valor que encuentre
        if (correo == null && !request.isEmpty()) {
            correo = request.values().iterator().next();
        }

        Map<String, Object> response = new HashMap<>();

        // Si de plano no llegó nada en la petición
        if (correo == null || correo.trim().isEmpty()) {
            response.put("status", "DENEGADO");
            response.put("mensaje", "No se recibió ningún dato o correo válido.");
            return ResponseEntity.ok(response);
        }

        // Limpiamos espacios en blanco accidentales que a veces generan los QR
        String correoFinal = correo.trim();

        // Buscar al socio en la base de datos por su correo
        return socioRepository.findByCorreo(correoFinal)
            .map(socio -> {
                // El cliente de Flutter busca que el estado sea exactamente "PERMITIDO"
                response.put("status", "PERMITIDO");
                response.put("nombre", socio.getNombre()); // Extrae el nombre de Socio.java
                response.put("mensaje", "¡Acceso Autorizado! Bienvenido de vuelta.");
                return ResponseEntity.ok(response);
            })
            .orElseGet(() -> {
                // Si el correo no existe en la base de datos limpia, rebota el acceso
                response.put("status", "DENEGADO");
                response.put("mensaje", "Socio no encontrado con el correo: " + correoFinal);
                return ResponseEntity.ok(response);
            });
    }
}