package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Venta;
import com.restobar.proyecto.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> list(){
        return ventaRepository.findAll();
    }

    public Optional<Venta> getOne(Integer id){
        return  ventaRepository.findById(id);
    }

    public void save(Venta venta){
        ventaRepository.save(venta);
    }

    public void delete(){
        ventaRepository.deleteAll();
    }

    public Integer findById(){
        return ventaRepository.findById();
    }
}
