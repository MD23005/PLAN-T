package com.libcode.plant.plant.grupo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.grupo.entities.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    Grupo findByNombre(String nombre);
}
