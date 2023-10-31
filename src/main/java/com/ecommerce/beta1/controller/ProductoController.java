package com.ecommerce.beta1.controller;


import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller

@RequestMapping("/productos")
public class ProductoController {

    // private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("") // Esta anotacion no tiene ningun valor
    public String show(){
        return "productos/show";
    }


    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

}
