package com.libcode.crud.crud.tutor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email;
    
<<<<<<< HEAD
    // Constructores
=======
    // Constructores, getters y setters
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
    public Tutor() {}
    
    public Tutor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}