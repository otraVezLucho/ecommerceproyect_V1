package com.ecommerce.beta1.service;


import com.ecommerce.beta1.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService  {

    public Producto save(Producto producto);

    //El metodo optional verifica si el producto existe o no
    public Optional<Producto>get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();


}
