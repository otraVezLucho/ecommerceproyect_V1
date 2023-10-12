package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//aca se crean las clases de service que llaman al producto repository para hacer  o llamar a  los metodos CRUD
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> { // Producto es la interfaz sepa a donde tiene que hacer el CRUD y el segundo valor es el id de la clase

}
