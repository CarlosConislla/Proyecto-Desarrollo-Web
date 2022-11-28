package com.restobar.proyecto.repository;

import com.restobar.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
