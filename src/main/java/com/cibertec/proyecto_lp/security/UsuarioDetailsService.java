package com.cibertec.proyecto_lp.security;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto_lp.entity.Usuario;
import com.cibertec.proyecto_lp.repository.UsuarioRepository;


@Service
public class UsuarioDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        String rolConPrefijo = "ROLE_" + usuario.getRol().getNombreRol();
        return new User(
                usuario.getCorreoUsuario(),
                usuario.getContraseña(),
                Collections.singletonList(new SimpleGrantedAuthority(rolConPrefijo))
        );
    }
}