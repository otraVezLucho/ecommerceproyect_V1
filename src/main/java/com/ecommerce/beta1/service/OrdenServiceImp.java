package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.model.Usuario;
import com.ecommerce.beta1.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImp implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository; // sirve para los Metodos CRUD

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();

    }

    //Revisar V.32

    public String generadorNumeroOrden(){
        int numero = 0;
        String numeroConcatenado ="";//devuelve el string con el secuecial de la orden

        List<Orden> ordenes =findAll(); // la idea es obtener todas la ordenes y obtener el ultimo numero ingresado de esa orden
        List <Integer> numeros = new ArrayList<Integer>(); // en esta lista se va a colocar los numeros de las ordenes de la lista ordenes, se debe hacer cast a numeros porque la lista ordenes es de strings
        ordenes.stream().forEach(o-> numeros.add(Integer.valueOf(o.getNumero())));// se pasa el numero de orden de que viene de la lista ordenes, se recorre la lista y se agregan los numeros a la lista 'numeros' pero en formato int

        //Obtener el ultimo numero de la orden para saber cual debe ser el siguiente numero de la orden
        if (ordenes.isEmpty()){
             numero =1;
        } else{
            numero = numeros.stream().max(Integer::compare).get(); // obtiene el mayor numero de esa lista
            numero++;
        }
        //Ahora se debe volver a pasar el numero a string con formato 0000011
        if (numero<10){
            numeroConcatenado="000000000"+String.valueOf(numero);
        }else if (numero <100){
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if (numero <1000){
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if (numero <10000){
            numeroConcatenado="000000"+String.valueOf(numero);
        }else if (numero <100000){
            numeroConcatenado="00000"+String.valueOf(numero);
        }

        return numeroConcatenado; // IMPORTANTEEEE va a devolver el string con el secuencial del numero de la orden
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }
}
