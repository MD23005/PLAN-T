package com.libcode.crud.crud.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.crud.crud.planificacion.entities.Planificacion;

public interface PlanificacionRepository extends JpaRepository<Planificacion, Long> {
}  