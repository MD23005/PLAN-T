package com.libcode.plant.plant.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libcode.plant.plant.admin.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}