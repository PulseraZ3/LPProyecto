package com.cibertec.proyecto_lp.repository;

import com.cibertec.proyecto_lp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreoUsuario(String correoUsuario);
}
