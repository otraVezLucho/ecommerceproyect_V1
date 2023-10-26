package com.ecommerce.beta1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    //REDIRECCIONAR A EL ARCHIVO home.html
    @GetMapping("")
    public String home(){
        return "administrador/home";
    }
}
