package com.ecommerce.beta1.controller;

import jakarta.persistence.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping("") // Esta anotacion no tiene ningun valor
    public String show(){
        return "productos/show";
    }
}
