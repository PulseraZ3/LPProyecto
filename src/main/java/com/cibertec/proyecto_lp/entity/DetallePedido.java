package com.cibertec.proyecto_lp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "detallepedido")
public class DetallePedido {
    @EmbeddedId
    private DetallePedidoId id;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name="id_producto")
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @ManyToOne
    @MapsId("idPedido") 
    @JoinColumn(name = "id_Pedido")
    private Pedido pedido;


    public DetallePedido() {}


    public DetallePedido(DetallePedidoId id, Producto producto, Integer cantidad, Double precioUnitario,
            Pedido pedido) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.pedido = pedido;
    }


    public DetallePedidoId getId() {
        return id;
    }


    public void setId(DetallePedidoId id) {
        this.id = id;
    }


    public Producto getProducto() {
        return producto;
    }


    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public Integer getCantidad() {
        return cantidad;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public Double getPrecioUnitario() {
        return precioUnitario;
    }


    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


    public Pedido getPedido() {
        return pedido;
    }


    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    
}
