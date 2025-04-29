package com.libcode.plant.plant.tutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.tutor.entities.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}