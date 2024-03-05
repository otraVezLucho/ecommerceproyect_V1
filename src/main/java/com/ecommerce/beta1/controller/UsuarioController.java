package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.IOrdenService;
import com.ecommerce.beta1.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService; //Para poder utilizar las operaciones crud

    @Autowired
    private IOrdenService ordenService; //Para poder utilizar las operaciones crud

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
        logger.info("Usuario obtenido de DB: {}", user); // si agrega un .get() va a dar error cuando se ingrese un usuario incorrecto porque los valores van a ser null

            // IF DE VALIDACION MOMENTANIA SOLO ES PARA GUARDAR LA SESION, ANTES DE USAR SPRING SECURITY
            if (user.isPresent()){

                session.setAttribute("idusuario",user.get().getId()); // primer parametro sera un nombre y el segundo su valor en este caso el id del usuario
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
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session){
        model.addAttribute("sesion",session.getAttribute("idusuario"));

        //Primero se identifica el usuario para poder adquirir las ordenes que ha realizado
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        //Se buscan las ordenes y se guardan en una lista que recive objetos de tipo Orden
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        //A travez del model se le pasa la lista que contiene la lista de ordenes
        //NOTA: Esta lista se recorre desde la pagina de compras.html con las funciones de thymeleaf
        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }


    //Revisar V.43
    @GetMapping("/detalle/{id}")
    public  String detalleCompra (@PathVariable Integer id, HttpSession session,Model model){ // @PathVariable para permitir matear el parametro que viene en la url

        logger.info("id de la irden: {}",id); // Mostrar la info por consola del detalle de la compra por id
        //detalles de la orden por medio de un metodo de IOrdenService
        Optional<Orden> orden = ordenService.findById(id);

        //Enviar la informacion
        //Lo que hace JPA es que cuando se llama la orden tambien trae los detalles como atributo
        model.addAttribute("detalles",orden.get().getDetalle());
        //session
        model.addAttribute("sesion",session.getAttribute("idusuario"));

        return "usuario/detallecompra"; // retorna a la pagina detallecompra.html
    }

    //Revisar V.44
    @GetMapping("/cerrar")
    public String cerrarSesion (HttpSession session){
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
