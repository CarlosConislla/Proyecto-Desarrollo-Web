package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Categoria;
import com.restobar.proyecto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    //obtener la lista de categorias
    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }

    //obtener una categoria por id
    public Optional<Categoria> getOne(Integer id){
        return categoriaRepository.findById(id);
    }

    //guardar categoria
    public void save(Categoria categoria){
        categoriaRepository.save(categoria);
    }

    //eliminar por id
    public void delete(Integer id){
        categoriaRepository.deleteById(id);
    }

    //existe por id
    public boolean existsById(Integer id){
        return categoriaRepository.existsById(id);
    }

    //existe por nombre
    public boolean existsByNombre(String nombre){
        return categoriaRepository.existsByNombre(nombre);
    }

    //obtener una cat por nombre
    public Optional<Categoria> getByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }

}
