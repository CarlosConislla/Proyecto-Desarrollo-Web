package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.DetalleVenta;
import com.restobar.proyecto.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> list(){
        return detalleVentaRepository.findAll();
    }

    public void save(DetalleVenta detalleVenta){
        detalleVentaRepository.save(detalleVenta);
    }

    public void delete(Integer id){
        detalleVentaRepository.deleteById(id);
    }
}
