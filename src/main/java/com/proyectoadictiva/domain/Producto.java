package com.proyectoadictiva.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String nombre;
    private String categoria;
    private String descripcion;
    private String detalle;
    private double precio;
    private String rutaImagen;
    private boolean activo = true;
}
