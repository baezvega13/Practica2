package com.proyectoadictiva.controller;

import com.proyectoadictiva.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoService.listarActivos());
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo/catalogo";
        
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros() {
        return "sobreNosotros/sobreNosotros";
    }

    @GetMapping("/faq")
    public String preguntasFrecuentes() {
        return "faq/faq";
    }
}
