package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Role;
import com.restobar.proyecto.modelo.Usuario;
import com.restobar.proyecto.repository.RoleRepository;
import com.restobar.proyecto.repository.UserRepository;
import com.restobar.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Usuario> listAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        Usuario objUser = usuario;

        objUser.setPassword(passwordEncoder.encode(objUser.getPassword()));

        Role role = new Role();
        // Se asigna el rol con el que queremos que el usuario se cree
        role.setAuthority("ROLE_USER");
        role = roleRepository.save(role);

        objUser.getRoles().add(role);

        objUser = userRepository.save(usuario);
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
