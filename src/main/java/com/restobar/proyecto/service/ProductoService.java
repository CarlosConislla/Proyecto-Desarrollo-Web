package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Producto;
import com.restobar.proyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> list(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(Integer id){
        return productoRepository.findById(id);
    }

    public void save(Producto producto){
        productoRepository.save(producto);
    }

    public void delete(Integer id){
        productoRepository.deleteById(id);
    }

    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }
}
