package com.ecommerce.beta1.controller;

import com.ecommerce.beta1.model.DetalleOrden;
import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/") // la linea sola indica que se debe dirigir a la raiz
public class HomeController {

    //Importante usar el paquete de slf4j
    private final Logger log = LoggerFactory.getLogger(HomeController.class); //imprime el objeto solicitado en consola

    @Autowired//para que inyecte el contenedor del framework
    private ProductoService productoService; // para obtener los productos

    //Esta lista es para almacenar el detalle de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Almacena los datos de la orden
    Orden orden = new Orden();


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



    // Funcionalidad para poder agragar al carrito...
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad, Model model){

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0; // Se coloca en 0 para que en la siguiente orden el carro quede de nuevo en 0

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("producto añadido: {}",optionalProducto.get()); // ver en consola la info del producto
        log.info("Cantidad: {}",cantidad); // ver en consola la cantidad
        producto = optionalProducto.get();

        //se debe settear todos los atributos de la clase detalleOrden o se va a generar un error de tipo server 500 porque seguramente retornara algun valor como null
        detalleOrden.setCantidad(String.valueOf(cantidad));
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad); //
        detalleOrden.setProducto(producto); // esta asignacion se usa para poder asignar la clave foranea

        detalles.add(detalleOrden);// añade cada producto orden a la lista

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum(); // La variable dt está representando cada elemento de la lista detalles. Esta recorriendo la lista de detalles, tomando el total de cada uno y sumándolos y guardando el valor de la suma en la variable sumaTotal
        orden.setTotal(sumaTotal);

        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
        return"usuario/carrito";
    }

    //Quitar productos del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id,Model model){

        //Quitar el producto de la lista y actualizar o recontar los totales y actualizar el carrito

        //Lista nueva de productos para poder buscar el producto que se quiere borrar
        List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalleOrden: detalles){
            //Si encuentra un id que ya este en detalles, lo va a agregar a la lista de ordenes creada anteriormente
            if (detalleOrden.getProducto().getId()!=id){
                ordenNueva.add(detalleOrden);
            }
        }
        //poner la nueva lista con los productos restantes
        detalles=ordenNueva;

        //sumatoria para recalcular los productos
        double sumaTotal =0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum(); // La variable dt está representando cada elemento de la lista detalles. Esta recorriendo la lista de detalles, tomando el total de cada uno y sumándolos y guardando el valor de la suma en la variable sumaTotal

        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);

        return "usuario/carrito";
    }
}


















