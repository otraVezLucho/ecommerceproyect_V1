package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService; //Para poder utilizar las operaciones crud

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    ///usuario/registro
    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }
    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info("Usuario registro: {}",usuario);
        //tipo de usuario
        usuario.setTipo("USER");
        usuarioService.save(usuario); // Para guardar el usuario en la base de datos
        return "redirect:/"; //redirige a la raiz
    }
}
