package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrdenServiceImp implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository; // sirve para los Metodos CRUD

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);

    }
}
