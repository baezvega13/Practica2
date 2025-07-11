package com.proyectoadictiva.controller;

import com.proyectoadictiva.domain.*;
import com.proyectoadictiva.service.OrdenService;
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
        double totalOrden = 0;

        for (Map<String, Object> item : carrito) {
            Producto producto = (Producto) item.get("producto");
            int cantidad = (int) item.get("cantidad");
            double precioUnitario = (double) item.get("precio");

            DetalleOrden detalle = new DetalleOrden();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setTotalProducto(precioUnitario * cantidad);
            detalle.setOrden(orden);

            orden.getDetalles().add(detalle);
            totalOrden += detalle.getTotalProducto();
        }

        orden.setTotal(totalOrden);
        ordenService.guardarOrden(orden);
        session.removeAttribute("carrito");

        model.addAttribute("codigo", orden.getCodigoRetiro());
        return "ordenConfirmada"; 
    }
}
