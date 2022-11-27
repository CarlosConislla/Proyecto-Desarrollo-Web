package com.restobar.proyecto.service;

import com.restobar.proyecto.modelo.Role;

import java.util.List;

public interface RoleService {

    public List<Role> listAll();

    public void save(Role role);

    Role FindById(Long id);

    void DeleteById(Long id);

}
