package com.libcode.plant.plant.planificacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.planificacion.entities.Planificacion;
import com.libcode.plant.plant.tutor.entities.Tutor;

public interface PlanificacionRepository extends JpaRepository<Planificacion, Long> {
    List<Planificacion> findByTutor(Optional<Tutor> tutor);
}  