package com.cibertec.proyecto_lp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto_lp.dto.CategoriaVendidaDTO;
import com.cibertec.proyecto_lp.entity.Categoria;
import com.cibertec.proyecto_lp.repository.CategoriaRepository;
import com.cibertec.proyecto_lp.serviceInterface.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @Override
    public List<Categoria> listarTodo() {
        return repo.findAll();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return repo.save(categoria);
    }

    @Override
    public Categoria buscarId(int id_categoria) {
        return repo.findById(id_categoria).orElse(null);
    }

    @Override
    public void eliminar(int id_categoria) {
        repo.deleteById(id_categoria);
    }

    @Override
    public Page<Categoria> listarPaginadoPage(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina, tamano);
        return repo.findAll(pageable);
    }

    @Override
    public Page<Categoria> listarPorEstado(String estado, int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina, tamano);
        return repo.findByEstado(estado, pageable);
    }

    @Override
    public List<CategoriaVendidaDTO> obtenerCategoriaVendidas(){
        return repo.obtenerCategoriaVendidas();
    }
}
