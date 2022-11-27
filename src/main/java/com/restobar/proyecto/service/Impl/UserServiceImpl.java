package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Usuario;
import com.restobar.proyecto.repository.UserRepository;
import com.restobar.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Usuario> listAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        userRepository.save(usuario);
    }

    @Override
    public Usuario FindById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void DeleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
