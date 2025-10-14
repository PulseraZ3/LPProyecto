package com.cibertec.proyecto_lp.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;


import com.cibertec.proyecto_lp.entity.Producto;

public interface ProductoService {
    List<Producto> listarTodo();
    Producto guardar(Producto producto);
    Producto buscarId(int idProducto);
    void eliminar(int idProducto);
    Page<Producto> listarPaginadoPage(int pagina, int tamano);
    Page<Producto> listarPorEstado(String estado, int pagina, int tamano);
    List<Producto> listarPorEstadoSin(String estado);
    Page<Producto> listarPorEstadoCategoria(String estado, int idCategoria, int pagina, int tamano);
}
