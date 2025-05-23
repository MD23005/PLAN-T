package com.libcode.plant.plant.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.planificacion.entities.Planificacion;

public interface PlanificacionRepository extends JpaRepository<Planificacion, Long> {
}  