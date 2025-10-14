package com.cibertec.proyecto_lp.dto;

import java.time.LocalDateTime;

public class VentasDiaDTO {
    private LocalDateTime fechaPedido;
    private Double ventaTotal;

    public VentasDiaDTO(LocalDateTime fechaPedido, Double ventaTotal) {
        this.fechaPedido = fechaPedido;
        this.ventaTotal = ventaTotal;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(Double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }
}
