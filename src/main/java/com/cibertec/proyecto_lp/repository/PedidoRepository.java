package com.cibertec.proyecto_lp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cibertec.proyecto_lp.dto.MetodoPagoDTO;
import com.cibertec.proyecto_lp.dto.VentasDiaDTO;
import com.cibertec.proyecto_lp.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer>{
    @Query("Select new com.cibertec.proyecto_lp.dto.VentasDiaDTO(p.fechaPedido, SUM(dp.cantidad * dp.precioUnitario)) " +
                "From Pedido p Join p.detallePedidos dp " + 
                "Group by p.fechaPedido Order by p.fechaPedido")
    List<VentasDiaDTO> obtenerVentas();

    @Query("Select new com.cibertec.proyecto_lp.dto.MetodoPagoDTO(p.metodoPago, count(p.metodoPago)) "+
                "From Pedido p "+
                "Group by p.metodoPago")
    List<MetodoPagoDTO> obtenerPago();

}
