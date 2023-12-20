package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // la linea sola indica que se debe dirigir a la raiz
public class HomeController {

    @Autowired//para que inyecte el contenedor del framework
    private ProductoService productoService; // para obtener los productos

    @GetMapping("")
    public String home (Model model){

        model.addAttribute("productos", productoService.findAll() ); // productoService.findAll() va a traer todos los productos para colocarlos en la variable productos
        return "usuario/home";
    }
}
