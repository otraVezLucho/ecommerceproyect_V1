package com.ecommerce.beta1.controller;



import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.service.ProductoService;
import com.ecommerce.beta1.service.UploadFileService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

//import java.util.logging.Logger; cuando se usa org.slf4j.* no se usa esta por eso la comento

@Controller

@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class); //busca por nombre de la clase

    @Autowired
    // para que no haya que crear manualmente un objeto sin tener que instanciarlo y que lo haga el mismo contenedor de Spring
    private ProductoService productoService;

    @Autowired
    private UploadFileService upload;

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
    public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException { // Con la anotacion le indicamos de donde se debe traer el producto de la pagina de create.html linea 80 de id y name que corresponden al campo img y guardarlo en la variable file y finalmente se manda a la logica de imagen para que la pueda usar saveImage
        LOGGER.info("este es el objeto producto {}", producto); // El metodo toString de la entidad Producto es lo que se va a mostrar en consola si no hay error y esa es la funcion de LOOGER
        // Antes de hacer el guardado se necesita crear un usuario de prueba y asignarlo para que no salga error porque no hay usuario asignado
        Usuario usuario1 = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario1);
        //////////////////////// Logica para guardar imagen

        if(producto.getId()==null){ // esta validacion se hace cuando se crea un producto
            String  nombreImagen = upload.saveImage(file); // esto apunta a la clase servicio UploadFileService y este metodo genera una exception que debe ser capturada a nivel de metodo
            producto.setImagen(nombreImagen);
        }//////////////////////////// para cuando se edita un producto pero no se cambia la imagen debe cargar la misma imagen
        else{
            if(file.isEmpty()){
                Producto p = new Producto();
                p = productoService.get(producto.getId()).get();
                producto.setImagen(p.getImagen());
            }else { // guardar una imagen nueva
                String  nombreImagen = upload.saveImage(file); // esto apunta a la clase servicio UploadFileService y este metodo genera una exception que debe ser capturada a nivel de metodo
                producto.setImagen(nombreImagen);
            }
        }

        ////////////////////////
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
