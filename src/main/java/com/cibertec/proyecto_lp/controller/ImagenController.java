package com.cibertec.proyecto_lp.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ImagenController {

    private final Path rutaUploads = Paths.get("uploads");


    @GetMapping("/uploads/{nombreImagen:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen) {//devuelve la imagen al navegador resource archivos de sistema
        try {
            Path archivo = rutaUploads.resolve(nombreImagen).toAbsolutePath();//construir ruta completa del archivo
            Resource recurso = new UrlResource(archivo.toUri()); //crear recurso a partir de la ruta del archivo

            if (recurso.exists() || recurso.isReadable()) {// si existe y es legible 
                return ResponseEntity.ok() //retorna una respuesta 200, 200 = exito 
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"") // mostrar imagen en el navegador
                        .body(recurso);//incluir el archivo en el body
            } else {
                return ResponseEntity.notFound().build(); //404 = no encontrado
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
