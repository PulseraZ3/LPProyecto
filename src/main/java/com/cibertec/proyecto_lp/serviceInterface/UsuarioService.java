package com.cibertec.proyecto_lp.serviceInterface;

import com.cibertec.proyecto_lp.entity.Usuario;

public interface UsuarioService {
    Usuario registrar(Usuario usuario);
    Usuario login(String correoUsuario, String contraseña);
}
