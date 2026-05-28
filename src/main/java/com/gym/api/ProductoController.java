package com.gym.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductoController {

    private static List<Producto> inventario = new ArrayList<>();

    // Bloque estático para simular los productos de la tienda en memoria
    static {
        inventario.add(new Producto(1L, "Proteína Whey Gold 2lbs", 850.00, 12, "Suplementos"));
        inventario.add(new Producto(2L, "Creatina Micronizada Birdman", 520.00, 3, "Suplementos")); // Stock bajo de prueba
        inventario.add(new Producto(3L, "Pre-Entreno Psychotic", 640.00, 8, "Suplementos"));
        inventario.add(new Producto(4L, "Agua Purificada 1L", 20.00, 45, "Bebidas"));
        inventario.add(new Producto(5L, "Bebida Energética Monster", 45.00, 22, "Bebidas"));
        inventario.add(new Producto(6L, "Shaker CMMP GYM Black", 150.00, 15, "Accesorios"));
    }

    // 1. Obtener todos los productos (GET)
    @GetMapping("/productos")
    public List<Producto> obtenerInventario() {
        return inventario;
    }

    // 2. Actualizar/Recargar stock de un producto (POST)
    @PostMapping("/productos/recargar")
    public ResponseEntity<?> recargarStock(@RequestBody Map<String, Object> payload) {
        try {
            Long idProducto = Long.valueOf(payload.get("id").toString());
            int cantidadNueva = Integer.parseInt(payload.get("cantidad").toString());

            for (Producto p : inventario) {
                if (p.getId().equals(idProducto)) {
                    p.setStock(p.getStock() + cantidadNueva); // Sumamos el nuevo inventario
                    return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "mensaje", "Inventario actualizado. Nuevo stock: " + p.getStock(),
                        "producto", p.getNombre()
                    ));
                }
            }
            return ResponseEntity.status(404).body(Map.of("status", "ERROR", "mensaje", "Producto no encontrado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("status", "ERROR", "mensaje", "Datos inválidos"));
        }
    }
}