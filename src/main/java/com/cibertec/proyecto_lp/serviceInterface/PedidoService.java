package com.cibertec.proyecto_lp.serviceInterface;

import java.util.List;

import com.cibertec.proyecto_lp.dto.MetodoPagoDTO;
import com.cibertec.proyecto_lp.dto.VentasDiaDTO;

public interface PedidoService {
    List<VentasDiaDTO> obtenerVentasPorDia();
    List<MetodoPagoDTO> obtenerMetodoPago();
}
