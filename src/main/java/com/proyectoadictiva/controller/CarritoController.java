package com.proyectoadictiva.controller;

import com.proyectoadictiva.domain.Producto;
import com.proyectoadictiva.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<Map<String, Object>> carrito = (List<Map<String, Object>>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();
        double total = carrito.stream()
                .mapToDouble(p -> (double) p.get("subtotal"))
                .sum();
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long idProducto, @RequestParam int cantidad, HttpSession session) {
        Producto producto = productoService.buscarPorId(idProducto);
        List<Map<String, Object>> carrito = (List<Map<String, Object>>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();

        Map<String, Object> item = new HashMap<>();
        item.put("producto", producto);
        item.put("cantidad", cantidad);
        item.put("precio", producto.getPrecio());
        item.put("subtotal", producto.getPrecio() * cantidad);

        carrito.add(item);
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/carrito";
    }
}
