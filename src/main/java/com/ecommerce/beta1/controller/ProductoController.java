package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Producto;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController  {

    // variable tipo logger para hacer tests en el backend
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    //Metodos para redirigir

    @GetMapping("")//Se usa con comillas vacias para que se mapee a lo que indica productos
    public String show(){
        return "productos/show";
    }

    //Este metodo se va a encargar de redireccionar a la pagina create
    @GetMapping("/create")
    public String create(){ return "productos/create";}


    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("este es el objeto producto {}",producto);
        return "redirect:/productos ";
    }


}
