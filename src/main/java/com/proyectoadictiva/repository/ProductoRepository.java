package com.proyectoadictiva.repository;

import com.proyectoadictiva.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
