package com.cibertec.proyecto_lp.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cibertec.proyecto_lp.dto.CategoriaVendidaDTO;
import com.cibertec.proyecto_lp.entity.Categoria;

public interface CategoriaService {

    List<Categoria> listarTodo();
    Categoria guardar(Categoria categoria);
    Categoria buscarId(int idCategoria);
    void eliminar(int idCategoria);
    Page<Categoria> listarPaginadoPage(int pagina, int tamano);
    Page<Categoria> listarPorEstado(String estado, int pagina, int tamano);
    List<CategoriaVendidaDTO> obtenerCategoriaVendidas();
}
