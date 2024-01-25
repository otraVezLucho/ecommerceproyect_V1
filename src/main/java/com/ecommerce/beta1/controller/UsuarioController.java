package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


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

    //Metodo para mostrar vista login y hacer autenticacion con spring security
    @GetMapping("/login")
    public String login(){

        return "usuario/login";
    }
    //Validacion de usuario. Verificar V38
    @PostMapping("/acceder")
    public String acceder (Usuario usuario, HttpSession session){ // HttpSession session va a recibir el id del usuario activo para poder indicar cual es el usuario activo en la sesion y se puede usar en el resto de lugares que necesita usar en la aplicaion
        logger.info("Accesos : {}",usuario);
        //Validar que el correo este en la base de datos
        Optional<Usuario> user = usuarioService.findByCorreo(usuario.getCorreo());
        logger.info("Usuario obtenido de DB: {}", user);

            // IF DE VALIDACION MOMENTANIA SOLO ES PARA GUARDAR LA SESION, ANTES DE USAR SPRING SECURITY
            if (user.isPresent()){

                session.setAttribute("idUsuario",user.get().getId()); // primer parametro sera un nombre y el segundo su valor en este caso el id del usuario
                if(user.get().getTipo().equals("ADMIN")){
                    return"redirect:/administrador";
                }else{
                    return"redirect:/";
                }
            }else{
                logger.info("Usuario no encontrado intente de nuevo:");
            }
        return"redirect:/";
    }
}
