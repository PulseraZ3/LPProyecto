package com.cibertec.proyecto_lp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cibertec.proyecto_lp.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoriaIdCategoria(int idCategoria);

    Page<Producto> findByEstado(String estado, Pageable pageable);

    List<Producto> findByEstado(String estado);

    Page<Producto> findByEstadoAndCategoriaIdCategoria(String estado, int idCategoria, Pageable pageable);
}