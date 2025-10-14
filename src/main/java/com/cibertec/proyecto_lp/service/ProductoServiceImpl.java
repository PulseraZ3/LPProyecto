package com.cibertec.proyecto_lp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto_lp.entity.Producto;
import com.cibertec.proyecto_lp.repository.ProductoRepository;
import com.cibertec.proyecto_lp.serviceInterface.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public Page<Producto> listarPaginadoPage(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina, tamano);
        return repo.findAll(pageable);
    }

    @Override
    public List<Producto> listarTodo() {
        return repo.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Producto buscarId(int idProducto) {
        return repo.findById(idProducto).orElse(null);
    }

    @Override
    public void eliminar(int idProducto) {
        repo.deleteById(idProducto);
    }

    @Override
    public Page<Producto> listarPorEstado(String estado, int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina, tamano);
        return repo.findByEstado(estado, pageable);
    }

    @Override
    public List<Producto> listarPorEstadoSin(String estado){
        return repo.findByEstado(estado);
    }

    @Override
    public Page<Producto> listarPorEstadoCategoria(String estado, int idCategoria, int pagina, int tamano){
        Pageable pageable = PageRequest.of(pagina,tamano);
    return repo.findByEstadoAndCategoriaIdCategoria(estado, idCategoria, pageable);    
    }
}
