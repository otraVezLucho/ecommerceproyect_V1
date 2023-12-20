package com.ecommerce.beta1;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Esta clase se va a encargar de configurar el path para puntar desde cualquier lugar a los recursos de imagenes que implementa una interfaz  WebMvcConfigurer
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer { // Esta interfaz trae los metodos para obtener la ubicacion de los recursos

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // el parametro es un path pattern que indica que se va a obtener de ahi todo lo que se tenga en el directorio
        registry.addResourceHandler("/images/**").addResourceLocations("file:images/"); // el segundo metodo es como una indicacion a donde deberia apuntar y asi poder mostrar las imagenes en cualquier lugar de la pagina.
    }
}
