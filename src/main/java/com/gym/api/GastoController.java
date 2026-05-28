package com.gym.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GastoController {

    // Lista temporal en memoria para simular la base de datos de egresos
    private static final List<Map<String, Object>> listaGastos = new ArrayList<>();

    // Bloque estático para cargar datos de prueba iniciales
    static {
        Map<String, Object> g1 = new HashMap<>();
        g1.put("id", 1);
        g1.put("descripcion", "Pago de Luz Eléctrica CFE");
        g1.put("monto", 1450.00);
        g1.put("categoria", "SERVICIOS");
        g1.put("fecha", "2026-05-28T10:00:00.000Z");

        Map<String, Object> g2 = new HashMap<>();
        g2.put("id", 2);
        g2.put("descripcion", "Mantenimiento de Caminadoras");
        g2.put("monto", 1200.50);
        g2.put("categoria", "MANTENIMIENTO");
        g2.put("fecha", "2026-05-27T15:30:00.000Z");

        Map<String, Object> g3 = new HashMap<>();
        g3.put("id", 3);
        g3.put("descripcion", "Artículos de Limpieza y Sanitizante");
        g3.put("monto", 449.50);
        g3.put("categoria", "LIMPIEZA");
        g3.put("fecha", "2026-05-26T09:15:00.000Z");

        listaGastos.add(g1);
        listaGastos.add(g2);
        listaGastos.add(g3);
    }

    // 1. Obtener el historial de gastos (GET /api/gastos)
    @GetMapping("/gastos")
    public List<Map<String, Object>> obtenerGastos() {
        return listaGastos;
    }

    // 2. Registrar un nuevo egreso (POST /api/gastos/registrar)
    @PostMapping("/gastos/registrar")
    public ResponseEntity<?> registrarGasto(@RequestBody Map<String, Object> nuevoGasto) {
        try {
            // Asignar un ID auto-incremental simulado
            int nuevoId = listaGastos.size() + 1;
            nuevoGasto.put("id", nuevoId);
            
            // Si la fecha no viene en el payload, la asignamos
            if (!nuevoGasto.containsKey("fecha")) {
                nuevoGasto.put("fecha", java.time.Instant.now().toString());
            }

            listaGastos.add(nuevoGasto);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("status", "OK");
            respuesta.put("mensaje", "Gasto registrado con éxito.");
            return ResponseEntity.ok(respuesta);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar el egreso");
        }
    }
}
