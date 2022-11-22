package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Mesa;
import com.restobar.proyecto.modelo.Reserva;

import java.util.List;

public interface ReservaService {

    public List<Reserva> listAll();

    public void save(Reserva reserva);

    Reserva FindById(Long id);

    void DeleteById(Long id);

}
