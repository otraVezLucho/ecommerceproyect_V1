package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//
@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
}
