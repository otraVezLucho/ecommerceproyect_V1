package com.ecommerce.beta1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController  {

    @GetMapping("")//Se usa con comillas vacias para que se mapee a lo que indica productos
    public String show(){
        return "productos/show";
    }
}
