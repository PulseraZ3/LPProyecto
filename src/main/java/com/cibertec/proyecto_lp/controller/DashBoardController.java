package com.cibertec.proyecto_lp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyecto_lp.dto.CategoriaVendidaDTO;
import com.cibertec.proyecto_lp.dto.MetodoPagoDTO;
import com.cibertec.proyecto_lp.dto.VentasDiaDTO;
import com.cibertec.proyecto_lp.serviceInterface.CategoriaService;
import com.cibertec.proyecto_lp.serviceInterface.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/ventas-dia")
    public List<VentasDiaDTO> obtenerVentasPorDia() {
        return pedidoService.obtenerVentasPorDia();
    }
    
    @GetMapping("/metodo-pago")
    public List<MetodoPagoDTO> obtenerTipoPago() {
        return pedidoService.obtenerMetodoPago();
    }
    
    @GetMapping("/categoria-mas-vendida")
    public List<CategoriaVendidaDTO> obtenerCategoriaVendidas() {
        return categoriaService.obtenerCategoriaVendidas();
    }
    
}
