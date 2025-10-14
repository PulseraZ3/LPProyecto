package com.cibertec.proyecto_lp.dto;

public class MetodoPagoDTO {
    private String metodoPago;
    private Long cantidad;

    public MetodoPagoDTO(String metodoPago, Long cantidad) {
        this.metodoPago = metodoPago;
        this.cantidad = cantidad;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }


}
