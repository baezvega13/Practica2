package com.proyectoadictiva.repository;

import com.proyectoadictiva.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    public List<Producto> findByCategoria(String categoria);
    
    public List<Producto> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);
}