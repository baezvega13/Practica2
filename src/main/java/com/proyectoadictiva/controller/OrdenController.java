package com.proyectoadictiva.controller;

import com.proyectoadictiva.domain.*;
import com.proyectoadictiva.service.OrdenService;
import com.proyectoadictiva.service.DetalleOrdenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @PostMapping("/generar-orden")
    public String generarOrden(HttpSession session, Model model) {
        List<Map<String, Object>> carrito = (List<Map<String, Object>>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/carrito";
        }

        Orden orden = new Orden();
        orden.setFecha(LocalDateTime.now());
        orden.setCodigoRetiro(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        orden.setDetalles(new ArrayList<>());
        
        
        orden = ordenService.save(orden); 
        
        double totalOrden = 0;

        for (Map<String, Object> item : carrito) {
            Producto producto = (Producto) item.get("producto");
            int cantidad = (int) item.get("cantidad");
            double precioUnitario = (double) item.get("precio");

            DetalleOrden detalle = new DetalleOrden();
            detalle.setOrden(orden);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(precioUnitario * cantidad); 

            
            detalleOrdenService.save(detalle);
            totalOrden += detalle.getSubtotal();
        }

        orden.setTotal(totalOrden);
        ordenService.save(orden); 

        session.removeAttribute("carrito");

        model.addAttribute("codigo", orden.getCodigoRetiro());
        return "orden/ordenConfirmada";
    }
}