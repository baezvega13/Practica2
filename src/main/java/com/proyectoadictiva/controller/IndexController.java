package com.proyectoadictiva.controller;

import com.proyectoadictiva.domain.Producto;
import com.proyectoadictiva.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("titulo", "INICIO");
        model.addAttribute("backUrl", "/");
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("titulo", "CATALOGO");
        model.addAttribute("backUrl", "/");
        return "catalogo/catalogo";
    }

    @GetMapping("/catalogo/{categoria}")
    public String listarProductosPorCategoria(@PathVariable String categoria, Model model) {
        List<Producto> productos;
        String titulo;
        String backUrl = "/catalogo";
        String vistaADevolver; 

        if ("todos".equals(categoria)) {
            productos = productoService.getProductos(true); 
            titulo = "TODOS LOS PRODUCTOS";
            vistaADevolver = "todos/todos"; 
        } else if ("panaderia".equals(categoria)) {
            productos = productoService.getProductosPorCategoria("Panadería");
            titulo = "PANADERÍA";
            vistaADevolver = "panaderia/panaderia"; 
        } else if ("reposteria".equals(categoria)) {
            productos = productoService.getProductosPorCategoria("Repostería"); 
            titulo = "REPOSTERÍA";
            vistaADevolver = "reposteria/reposteria"; 
        } else {
            
            productos = productoService.getProductos(true);
            titulo = "PRODUCTOS"; 
            vistaADevolver = "todos/todos"; 
        }

        model.addAttribute("titulo", titulo);
        model.addAttribute("backUrl", backUrl);
        model.addAttribute("productos", productos);
        return vistaADevolver; 
    }

    @GetMapping("/orden")
    public String orden() {
        return "orden/orden";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros(Model model) {
        model.addAttribute("titulo", "SOBRE NOSOTROS");
        model.addAttribute("backUrl", "/");
        return "sobreNosotros/sobreNosotros";
    }

    @GetMapping("/faq")
    public String preguntasFrecuentes(Model model) {
        model.addAttribute("titulo", "PREGUNTAS FRECUENTES");
        model.addAttribute("backUrl", "/");
        return "faq/faq";
    }

    @GetMapping("/detalle/{id}")
    public String getDetalleProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        if (producto == null) {
            return "redirect:/";
        }

        model.addAttribute("titulo", producto.getNombre());
        model.addAttribute("producto", producto);
  
        model.addAttribute("backUrl", "/catalogo/todos"); 

        return "detalle/detalle";
    }

    @GetMapping("/catalogo/buscar")
    public String buscarProductos(@RequestParam(name = "query") String query, Model model) {
        List<Producto> productos = productoService.getProductosPorBusqueda(query);

        model.addAttribute("titulo", "RESULTADOS DE BÚSQUEDA");
        model.addAttribute("backUrl", "/catalogo");
        model.addAttribute("productos", productos);

        return "todos/todos"; 
    }
}