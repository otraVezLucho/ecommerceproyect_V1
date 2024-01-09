package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Orden;

import java.util.List;

public interface IOrdenService {

    Orden save(Orden orden); //Metodo de Interfaz para guardar una orden

    List<Orden> findAll(); // Metodo de interfaz para obtener una lista de ordenes o buscar una lista de ordenes

    //Revisar V.32
    String generadorNumeroOrden();
}

