package com.ecommerce.beta1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service ()
public class UploadFileService {
    private String folder="images//"; // Esta variable va a tener la ubicacion en el proyecto en donde se van a cargar las imagenes

    public String saveImage(MultipartFile file) throws IOException {
        // en casos que haya produyctos sin imagen se realiza la siguiente validacion
        if (!file.isEmpty()){
            //la imagen se debe pasar a bites para que pueda enviarse del cliente al servidor para eso se crea un arreglo
            byte [] bytes = file.getBytes(); // este arreglo por si solo genera una exception la cual hay que capturar y se coloca a nivel de metodo
            Path path = Paths.get(folder+file.getOriginalFilename()); // indicamos que se guarde en el path indicado
            Files.write(path,bytes); // la primer variable es la ruta de la imagen, y el segunda variable es la imagen en bytes
            return file.getOriginalFilename(); // retorna el nombre de la imagen subida
        }
        //Cuando no hay una imagen asignada se va a retornar una imagen por default guardad
        return "default.jpg";
    }

    public void deleteImage(String nombreImagen){
        String ruta = "images//";
        File file = new File(ruta+nombreImagen);
        file.delete();
    }
}
