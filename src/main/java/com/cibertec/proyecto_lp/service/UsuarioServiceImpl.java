package com.cibertec.proyecto_lp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto_lp.entity.Rol;
import com.cibertec.proyecto_lp.entity.Usuario;
import com.cibertec.proyecto_lp.repository.RolRepository;
import com.cibertec.proyecto_lp.repository.UsuarioRepository;
import com.cibertec.proyecto_lp.serviceInterface.UsuarioService;

@Repository
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository RolRepository;
    @Override
    public Usuario registrar(Usuario usuario) {
        Rol rol = RolRepository.findById(1).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario login(String correoUsuario, String contraseña) {
        Usuario user = usuarioRepository.findByCorreoUsuario(correoUsuario);
            System.out.println("Usuario encontrado: " + (user != null ? user.getCorreoUsuario() : "null"));
    System.out.println("Contraseña Formulario: " + contraseña);

         if (user != null) {
        System.out.println("Contraseña DB: " + user.getContraseña());
        if (user.getContraseña().equals(contraseña)) {
            System.out.println("Contraseña correcta.");
            return user;
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    } else {
        System.out.println("No se encontró usuario con ese correo.");
    }
    return null;
}
}
