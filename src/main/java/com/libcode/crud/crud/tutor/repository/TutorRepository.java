package com.libcode.crud.crud.tutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.crud.crud.tutor.entities.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}