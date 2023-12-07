package com.ecommerce.beta1.controller;



import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

//import java.util.logging.Logger; cuando se usa org.slf4j.* no se usa esta por eso la comento

@Controller

@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class); //busca por nombre de la clase

    @Autowired // para que no haya que crear manualmente un objeto sin tener que instanciarlo y que lo haga el mismo contenedor de Spring
    private ProductoService productoService;

    @GetMapping("") // Esta anotacion no tiene ningun valor
    public String show(Model model){ // El objeto tipo Model lleva informacion del back end a la vista, en este caso la lista de productos a la vista show
        model.addAttribute("productos",productoService.findAll()); // Se asigna como 1er valor el nombre del atributo y 2do valor el objeto que contiene la informacion en este caso una lista
        return "productos/show";
    }


    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("este es el objeto producto {}", producto); // El metodo toString de la entidad Producto es lo que se va a mostrar en consola si no hay error y esa es la funcion de LOOGER
        // Antes de hacer el guardado se necesita crear un usuario de prueba y asignarlo para que no salga error porque no hay usuario asignado
        Usuario usuario1 = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario1);
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")// redirige el link a la pagina edit con la informacion del id del producto selecionado
    public String edit(@PathVariable Integer id, Model model){ // la anotacion @PathVariable mapea el id que indica la url a la variable id de los parametros y model se usa para
        Producto producto = new Producto(); // este objeto tipo producto almacenar el producto buscado
        Optional<Producto> optionalProducto = productoService.get(id); // el metodo productoService.get(id) verifica si el id existe en la DB y lo asigna a optionalProducto para poderlo asignar a la variable producto
        producto = optionalProducto.get(); // trae el objeto que se solicito y para saber si lo encontro se usa LOGGER
        LOGGER.info("producto: {}",producto); // Trae a consola toda la info del producto para verificar si lo encontro...
        model.addAttribute("producto",producto); // El nombre de la variable es la misma que se coloco en el archivo html en este caso "producto"
        return"productos/edit";
    }

    @PostMapping("/update")
    public String update (Producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }

        @GetMapping("/delete/{id}")
        public String delete(@PathVariable Integer id){
        productoService.delete(id);
        return "redirect:/productos";
        }
}
