package com.ecommerce.beta1.service;


import com.ecommerce.beta1.model.Producto;

import java.util.Optional;

//Esta interfaz se encarga de guardar todos los metodos CRUD
public interface ProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id); // Retorna un optional de tipo producto y valida el objeto que se llama existe o no
    public void update(Producto producto);
    public void delete(Integer id);
}
