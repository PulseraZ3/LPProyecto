package com.cibertec.proyecto_lp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cibertec.proyecto_lp.entity.Distrito;
import com.cibertec.proyecto_lp.repository.DistritoRepository;
import com.cibertec.proyecto_lp.serviceInterface.DistritoService;

public class DistritoServiceImpl implements DistritoService{

    @Autowired
    private DistritoRepository repo;
    @Override
    public List<Distrito> listarTodo() {
        return repo.findAll();
    }

}
