package com.proyectoadictiva.repository;

import com.proyectoadictiva.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    
}