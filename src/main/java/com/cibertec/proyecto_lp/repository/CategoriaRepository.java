package com.cibertec.proyecto_lp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyecto_lp.dto.CategoriaVendidaDTO;
import com.cibertec.proyecto_lp.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
        Page<Categoria> findByEstado(String estado, Pageable pageable);

        @Query("SELECT new com.cibertec.proyecto_lp.dto.CategoriaVendidaDTO(c.nombreCategoria, SUM(dp.cantidad)) " +
                        "FROM DetallePedido dp " +
                        "JOIN dp.producto p " +
                        "JOIN p.categoria c " +
                        "GROUP BY c.idCategoria, c.nombreCategoria " +
                        "ORDER BY SUM(dp.cantidad) DESC")
        List<CategoriaVendidaDTO> obtenerCategoriaVendidas();
}
