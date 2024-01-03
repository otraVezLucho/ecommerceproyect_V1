package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> { // va a mapear a la clase usuario con el numero de id para identificarlo
}
