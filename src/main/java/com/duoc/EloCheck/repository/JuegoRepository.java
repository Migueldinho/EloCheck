package com.duoc.EloCheck.repository;

import com.duoc.EloCheck.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {
}