package com.libcode.crud.crud.planificacion.entities;

import jakarta.persistence.*;
import com.libcode.crud.crud.tutor.entities.Tutor;
<<<<<<< HEAD
import com.libcode.crud.crud.grupo.entities.Grupo;
=======
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
import java.time.LocalDateTime;

@Entity
@Table(name = "planificaciones")
public class Planificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
<<<<<<< HEAD
    @Column(name = "fecha", nullable = false)  
    private LocalDateTime fechaHora;
    
    @Column(nullable = false)
=======
    @Column(name = "fecha", nullable = false)  // Nombre exacto de la columna en PostgreSQL
    private LocalDateTime fechaHora;
    
    @Column(nullable = false)
    private String grupo;
    
    @Column(nullable = false)
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
    private String tema;
    
    @Column(nullable = false)
    private String modalidad;
<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

=======
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
    
    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;
    
    // Constructores
    public Planificacion() {
    }
    
<<<<<<< HEAD
    public Planificacion(LocalDateTime fechaHora, Grupo grupo, String tema, String modalidad, Tutor tutor) {
=======
    public Planificacion(LocalDateTime fechaHora, String grupo, String tema, String modalidad, Tutor tutor) {
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
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
    
<<<<<<< HEAD
    public Grupo getGrupo() {
        return grupo;
    }
    
    public void setGrupo(Grupo grupo) {
=======
    public String getGrupo() {
        return grupo;
    }
    
    public void setGrupo(String grupo) {
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630
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