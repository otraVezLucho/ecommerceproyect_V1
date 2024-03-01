package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Orden;
import com.ecommerce.beta1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> { //

    //Metodo que permite tener todas las ordenes por usuario

    List<Orden> findByUsuario(Usuario usuario);
}
