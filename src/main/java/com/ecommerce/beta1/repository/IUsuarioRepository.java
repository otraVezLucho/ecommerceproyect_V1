package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> { // va a mapear a la clase usuario con el numero de id para identificarlo


    //Se crea este metodo para obtener un usuario con el correo que se paso como parametro  ya que por defecto la interfaz los identifica por id
    Optional<Usuario> findByCorreo(String correo);
}
