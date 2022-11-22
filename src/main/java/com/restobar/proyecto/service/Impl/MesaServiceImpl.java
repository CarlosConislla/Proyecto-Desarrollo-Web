package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Mesa;
import com.restobar.proyecto.repository.MesaRepository;
import com.restobar.proyecto.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    MesaRepository mesaRepository;

    @Override
    public List<Mesa> listAll() {
        return mesaRepository.findAll();
    }

    @Override
    public void save(Mesa mesa) {
        mesaRepository.save(mesa);
    }

    @Override
    public Mesa FindById(Long id) {
        return mesaRepository.findById(id).get();
    }

    @Override
    public void DeleteById(Long id) {
        mesaRepository.deleteById(id);
    }
}
