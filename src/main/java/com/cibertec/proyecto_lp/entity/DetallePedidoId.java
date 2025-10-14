package com.cibertec.proyecto_lp.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class DetallePedidoId implements Serializable {

    @Column(name = "id_Producto")
    private Integer idProducto;

    @Column(name = "id_Pedido")
    private Integer idPedido;

    public DetallePedidoId() {}

    public DetallePedidoId(Integer idProducto, Integer idPedido) {
        this.idProducto = idProducto;
        this.idPedido = idPedido;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }


}