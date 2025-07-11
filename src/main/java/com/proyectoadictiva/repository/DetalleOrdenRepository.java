package com.proyectoadictiva.repository;

import com.proyectoadictiva.domain.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {
    
}
