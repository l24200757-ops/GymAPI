package com.gym.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // ◄ AGREGADO: Le dice a Spring que esto es la tabla de productos
public class Producto {

    @Id // ◄ AGREGADO: Define la llave primaria obligatoria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ◄ Autoincrementable (1, 2, 3...)
    private Long id;
    
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    // Constructor vacío obligatorio para Spring
    public Producto() {}

    // Constructor lleno
    public Producto(Long id, String nombre, double precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}