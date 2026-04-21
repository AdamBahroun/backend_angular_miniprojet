package com.adam.voiture.repository;

import com.adam.voiture.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRole(String role);
}