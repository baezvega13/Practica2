package com.proyectoadictiva.service;

import com.proyectoadictiva.domain.Producto;
import com.proyectoadictiva.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoRepository.findAll();
        if (activos) {
            lista.removeIf(p -> !p.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoRepository.findById(producto.getId()).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Producto> getProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
    
    
    @Transactional(readOnly = true)
    public List<Producto> getProductosPorBusqueda(String query) {
        
        return productoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }

    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }
}