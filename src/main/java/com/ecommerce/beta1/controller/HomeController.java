package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/") // la linea sola indica que se debe dirigir a la raiz
public class HomeController {

    //Importante usar el paquete de slf4j
    private final Logger log = LoggerFactory.getLogger(HomeController.class); //imprime el objeto solicitado en consola

    @Autowired//para que inyecte el contenedor del framework
    private ProductoService productoService; // para obtener los productos

    @GetMapping("")
    public String home (Model model){

        model.addAttribute("productos", productoService.findAll() ); // productoService.findAll() va a traer todos los productos para colocarlos en la variable productos
        return "usuario/home";
    }

    @GetMapping("productohome/{id}") // Indica que va a obtener la informacion de solo el producto que indica
    // la variable id
    public String productoHome(@PathVariable Integer id,Model model){
        Producto producto = new Producto();
        Optional<Producto> productoOptional= productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        log.info("LOGGER FUNCIONANDO: Id producto enviado como parametro {}",id);
        return"usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(){
        return"usuario/carrito";
    }

}
