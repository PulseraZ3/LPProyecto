package com.cibertec.proyecto_lp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto_lp.dto.MetodoPagoDTO;
import com.cibertec.proyecto_lp.dto.VentasDiaDTO;
import com.cibertec.proyecto_lp.repository.PedidoRepository;
import com.cibertec.proyecto_lp.serviceInterface.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<VentasDiaDTO> obtenerVentasPorDia() {
        return pedidoRepository.obtenerVentas();
    }

    @Override
    public List<MetodoPagoDTO> obtenerMetodoPago() {
        return pedidoRepository.obtenerPago();
    }
}
