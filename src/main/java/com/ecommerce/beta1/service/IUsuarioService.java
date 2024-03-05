package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    //Metodos

    List<Usuario> findAll();

    //Metodo que permite obtener un usuario de la base de datos
    Optional<Usuario> findById (Integer id);
    Usuario save (Usuario usuario );
    Optional<Usuario> findByCorreo(String correo);
}
