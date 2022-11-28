package com.restobar.proyecto.repository;

import com.restobar.proyecto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    @Query(
            value = "update productos set cantidad=? where id=?",
            nativeQuery = true
    )
    void updateCantidad(Integer id, Integer cantidad);
}
