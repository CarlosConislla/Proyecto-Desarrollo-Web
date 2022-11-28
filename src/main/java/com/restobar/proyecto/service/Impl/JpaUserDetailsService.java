package com.restobar.proyecto.service.Impl;

import com.restobar.proyecto.modelo.Role;
import com.restobar.proyecto.modelo.Usuario;
import com.restobar.proyecto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role role : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
                authorities);
    }
}
