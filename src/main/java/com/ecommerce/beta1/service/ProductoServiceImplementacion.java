package com.ecommerce.beta1.service;


import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Esta clase se encarga de implementar los metodos CRUD que lo implementa o hereda de la interfaz de ProductoService
@Service
public class ProductoServiceImplementacion implements ProductoService{

    //esta anotacion sirve para decir que estamos inyectando a esta clase un objeto
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto); // El metodo update es basicamente guardar una nueva version sobre la anterior
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
