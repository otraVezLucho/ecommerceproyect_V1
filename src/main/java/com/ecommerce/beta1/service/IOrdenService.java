package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {

    Orden save(Orden orden); //Metodo de Interfaz para guardar una orden

    Optional<Orden> findById(Integer id);

    List<Orden> findAll(); // Metodo de interfaz para obtener una lista de ordenes o buscar una lista de ordenes

    //Revisar V.32
    String generadorNumeroOrden();

    //Revisar V.42
    List<Orden> findByUsuario(Usuario usuario);
}

