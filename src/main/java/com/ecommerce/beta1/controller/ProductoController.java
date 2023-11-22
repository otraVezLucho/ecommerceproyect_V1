package com.ecommerce.beta1.controller;


import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.util.logging.Logger; cuando se usa org.slf4j.* no se usa esta por eso la comento

@Controller

@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class); //busca por nombre de la clase

    @Autowired // para que no haya que crear manualmente un objeto sin tener que instanciarlo y que lo haga el mismo contenedor de Spring
    private ProductoService productoService;
    @GetMapping("") // Esta anotacion no tiene ningun valor
    public String show(){
        return "productos/show";
    }


    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("este es el objeto producto {}",producto); // El metodo toString de la entidad Producto es lo que se va a mostrar en consola si no hay error y esa es la funcion de LOOGER
        // Antes de hacer el guardado se necesita crear un usuario de prueba y asignarlo para que no salga error porque no hay usuario asignado
        Usuario usuario1 = new Usuario(1,"","","","","","","");
        producto.setUsuario(usuario1);
        productoService.save(producto);
        return "redirect:/productos";
    }

}
