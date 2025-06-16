package com.libcode.crud.crud.grupo.repository;

import com.libcode.crud.crud.grupo.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    Grupo findByNombre(String nombre);
}