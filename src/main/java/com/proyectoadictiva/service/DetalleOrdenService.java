package com.proyectoadictiva.service;

import com.proyectoadictiva.domain.DetalleOrden;
import com.proyectoadictiva.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Transactional
    public void save(DetalleOrden detalleOrden) {
        detalleOrdenRepository.save(detalleOrden);
    }
}
