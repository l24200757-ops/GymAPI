package com.gym.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;    // ◄ Agregado para soportar objetos grandes
import jakarta.persistence.Column; // ◄ Agregado para definir el tipo de columna masiva

@Entity 
public class Socio {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String edad;
    private String correo;
    private String telefono;
    private String plan;

    @Lob // ◄ Agregado: Le avisa a JPA que guardará un archivo o texto gigante
    @Column(columnDefinition = "LONGTEXT") // ◄ Agregado: Rompe el límite de tamaño en la base de datos
    private String foto; 

    // Constructor vacío obligatorio para Spring
    public Socio() {}

    // Constructor completo (Incluye el id al inicio)
    public Socio(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String edad, String correo, String telefono, String plan, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.plan = plan;
        this.foto = foto;
    }

    // --- GETTER Y SETTER DEL ID (Obligatorios) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // --- GETTERS Y SETTERS EXISTENTES ---
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }
    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
}