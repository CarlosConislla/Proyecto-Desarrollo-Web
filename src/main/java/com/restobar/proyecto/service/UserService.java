package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Usuario;

import java.util.List;

public interface UserService {

    public List<Usuario> listAll();

    public void save(Usuario usuario);

    Usuario FindById(Integer id);

    void DeleteById(Integer id);
}
