package com.proyectoadictiva.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_orden")
public class DetalleOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id") 
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "producto_id") 
    private Producto producto;

    private int cantidad;
    
    @Column(name = "precio_unitario")
    private double precioUnitario;
    
    @Column(name = "subtotal")
    private double subtotal; // 
}