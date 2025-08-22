package com.proyectoadictiva.service;

import com.proyectoadictiva.domain.Orden;
import com.proyectoadictiva.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Transactional
    public Orden save(Orden orden) { 
        return ordenRepository.save(orden);
    }
}