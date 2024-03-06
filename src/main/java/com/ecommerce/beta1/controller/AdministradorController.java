package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.service.IOrdenService;
import com.ecommerce.beta1.service.IUsuarioService;
import com.ecommerce.beta1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProductoService productoService; // se usa para poder acceder a los producto

    @Autowired
    private IUsuarioService usuarioService;// permite acceder a los metodos CRUD
    @Autowired
    private IOrdenService ordenService; // permite acceder a los metodos CRUD

    //REDIRECCIONAR A EL ARCHIVO home.html y mostrar todos los productos
    //
    @GetMapping("")
    public String home( Model model){
        List<Producto> productos = productoService.findAll(); // La variable guarda la lista de todos los productos en la base de datos
        model.addAttribute("productos", productos); // elprimero parametro es el nombre de la variable con la que voy a enviar y recibir y el segundo es el contenido de la lista declarada anteriormente
        return "administrador/home";
    }

    //Revisar V.45
    @GetMapping("/usuarios")
    public String usuarios(Model model){

        model.addAttribute("usuarios",usuarioService.findAll());
        return"administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes",ordenService.findAll());
        return "administrador/ordenes";
    }

}
