package com.libcode.plant.plant.grupo.entities;

import jakarta.persistence.*;
import com.libcode.plant.plant.tutor.entities.Tutor;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Grupo() {}

    public Grupo(String nombre, Tutor tutor) {
        this.nombre = nombre;
        this.tutor = tutor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }
}
