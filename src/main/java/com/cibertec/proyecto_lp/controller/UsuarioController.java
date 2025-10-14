package com.cibertec.proyecto_lp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.proyecto_lp.entity.Usuario;
import com.cibertec.proyecto_lp.repository.DistritoRepository;
import com.cibertec.proyecto_lp.repository.UsuarioRepository;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registrar")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("distritos", distritoRepository.findAll());
        return "Register";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuarioRepository.save(usuario);

        return "redirect:/usuario/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login";
    }


   
    
}
