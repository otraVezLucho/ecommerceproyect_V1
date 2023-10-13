package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Producto;
import com.ecommerce.beta1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PruductoServiceImplementacion implements ProductoService {

    @Autowired // Sirve para imdicar que estamos inyectando a esta clase un objeto
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
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
    productoRepository.deleteById(id);
    }
}
