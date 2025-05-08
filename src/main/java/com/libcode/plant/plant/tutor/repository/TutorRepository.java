package com.libcode.plant.plant.tutor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.libcode.plant.plant.tutor.entities.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByEmailAndPassword(String email, String password);
}