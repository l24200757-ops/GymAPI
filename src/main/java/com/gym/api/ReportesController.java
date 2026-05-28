package com.gym.api;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReportesController {

    @GetMapping("/reportes/dashboard")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        
        // 📊 Valores de prueba fijos para alimentar tu pantalla premium de Flutter
        data.put("ingresosMembresias", 15450.00);
        data.put("ingresosTienda", 4200.50);
        data.put("gastosOperativos", 3100.00);
        data.put("sociosActivos", 128);
        data.put("accesosHoy", 45);
        data.put("productosVendidos", 18);
        
        // Flujo semanal simulado (Lunes a Domingo) para las barras de la gráfica
        double[] ventasSemana = {1200.0, 1500.0, 900.0, 2100.0, 1800.0, 2500.0, 1400.0};
        data.put("ventasSemana", ventasSemana);

        return data;
    }
}