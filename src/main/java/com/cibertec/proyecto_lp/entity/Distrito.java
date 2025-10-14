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
@Table(name = "distrito")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDistrito;
    @Column(name = "nombre_distrito")
    private String nombreDistrito;
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "distrito")
    private List<Usuario> Usuario;

    public Distrito() {
    }

    public Distrito(int idDistrito, String nombreDistrito, String estado,
            List<Usuario> usuario) {
        this.idDistrito = idDistrito;
        this.nombreDistrito = nombreDistrito;
        this.estado = estado;
        Usuario = usuario;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Usuario> getUsuario() {
        return Usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        Usuario = usuario;
    }

}
