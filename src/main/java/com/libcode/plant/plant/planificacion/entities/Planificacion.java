package com.libcode.plant.plant.planificacion.entities;

import jakarta.persistence.*;
import com.libcode.plant.plant.tutor.entities.Tutor;
import com.libcode.plant.plant.grupo.entities.Grupo;
import java.time.LocalDateTime;

@Entity
@Table(name = "planificaciones")
public class Planificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha", nullable = false)  
    private LocalDateTime fechaHora;
    
    @Column(nullable = false)
    private String tema;
    
    @Column(nullable = false)
    private String modalidad;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    
    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;
    
    // Constructores
    public Planificacion() {
    }
    
    public Planificacion(LocalDateTime fechaHora, Grupo grupo, String tema, String modalidad, Tutor tutor) {
        this.fechaHora = fechaHora;
        this.grupo = grupo;
        this.tema = tema;
        this.modalidad = modalidad;
        this.tutor = tutor;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public Grupo getGrupo() {
        return grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public String getTema() {
        return tema;
    }
    
    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public String getModalidad() {
        return modalidad;
    }
    
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    public Tutor getTutor() {
        return tutor;
    }
    
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
    // Métodos adicionales
    @Override
    public String toString() {
        return "Planificacion{" +
               "id=" + id +
               ", fechaHora=" + fechaHora +
               ", grupo='" + grupo + '\'' +
               ", tema='" + tema + '\'' +
               ", modalidad='" + modalidad + '\'' +
               ", tutor=" + tutor +
               '}';
    }
}  