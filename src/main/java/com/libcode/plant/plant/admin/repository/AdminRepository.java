package com.libcode.plant.plant.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.admin.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
    Optional<Admin> findByEmail(String email);
}