package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Mesa;

import java.util.List;

public interface MesaService {

    public List<Mesa> listAll();

    public void save(Mesa mesa);

    Mesa FindById(Long id);

    void DeleteById(Long id);

}
