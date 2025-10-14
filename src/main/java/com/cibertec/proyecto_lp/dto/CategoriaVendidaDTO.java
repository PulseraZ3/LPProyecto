package com.cibertec.proyecto_lp.dto;

public class CategoriaVendidaDTO {
    private String nombreCategoria;
    private Long totalVendidos;
    public CategoriaVendidaDTO(String nombreCategoria, Long totalVendidos) {
        this.nombreCategoria = nombreCategoria;
        this.totalVendidos = totalVendidos;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public Long getTotalVendidos() {
        return totalVendidos;
    }
    public void setTotalVendidos(Long totalVendidos) {
        this.totalVendidos = totalVendidos;
    }
    
}
