package com.cibertec.proyecto_lp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Column(name = "stock_producto")
    private int stockProducto;
    @Column(name = "precio_uni")
    private double precioUni;
    @Column(name = "estado")
    private String estado;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "marca")
    private String marca;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "color")
    private String color;

    public Producto() {
        super();
    }

    public Producto(int idProducto, Categoria categoria, String nombreProducto, int stockProducto, double precioUni,
            String estado, String imagen, String marca, String descripcion, String color) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
        this.precioUni = precioUni;
        this.estado = estado;
        this.imagen = imagen;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public double getPrecioUni() {
        return precioUni;
    }

    public void setPrecioUni(double precioUni) {
        this.precioUni = precioUni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
