package com.ecommerce.beta1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // la linea sola indica que se debe dirigir a la raiz
public class HomeController {

    @GetMapping("")
    public String home (){
        return "usuario/home";
    }
}
