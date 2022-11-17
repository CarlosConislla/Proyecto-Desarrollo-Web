package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Mensaje;
import com.restobar.proyecto.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> list(){
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> getOne(Integer id){
        return mensajeRepository.findById(id);
    }

    public void save(Mensaje mensaje){
        mensajeRepository.save(mensaje);
    }

    public void delete(Integer id){
        mensajeRepository.deleteById(id);
    }

    public void deleteAll(){
        mensajeRepository.deleteAll();
    }
}
