package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Role;
import com.restobar.proyecto.repository.RoleRepository;
import com.restobar.proyecto.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role FindById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void DeleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
