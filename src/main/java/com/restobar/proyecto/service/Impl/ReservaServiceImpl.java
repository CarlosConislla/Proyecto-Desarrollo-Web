package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Mesa;
import com.restobar.proyecto.modelo.Reserva;
import com.restobar.proyecto.repository.ReservaRepository;
import com.restobar.proyecto.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listAll() {
        return reservaRepository.findAll();
    }

    @Override
    public void save(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Override
    public Reserva FindById(Long id) {
        return reservaRepository.findById(id).get();
    }

    @Override
    public void DeleteById(Long id) {
        reservaRepository.deleteById(id);
    }
}
