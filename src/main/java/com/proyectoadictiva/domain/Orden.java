package com.proyectoadictiva.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "orden")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id; 

    @Column(name = "codigo_retiro")
    private String codigoRetiro;

    private LocalDateTime fecha;

    private double total;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles;
}