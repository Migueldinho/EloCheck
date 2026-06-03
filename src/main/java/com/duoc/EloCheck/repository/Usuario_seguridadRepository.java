package com.duoc.EloCheck.repository;

import com.duoc.EloCheck.model.Usuario_seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Usuario_seguridadRepository extends JpaRepository<Usuario_seguridad, Integer> {
    Optional<Usuario_seguridad> findByUsername(String username);
}
