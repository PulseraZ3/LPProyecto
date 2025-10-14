package com.cibertec.proyecto_lp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> Usuario;

    
    public Rol() {
    }


    public Rol(int idRol, String nombreRol, List<com.cibertec.proyecto_lp.entity.Usuario> usuario) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        Usuario = usuario;
    }


    public int getIdRol() {
        return idRol;
    }


    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }


    public String getNombreRol() {
        return nombreRol;
    }


    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }


    public List<Usuario> getUsuario() {
        return Usuario;
    }


    public void setUsuario(List<Usuario> usuario) {
        Usuario = usuario;
    }

    
}
