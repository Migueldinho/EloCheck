package com.duoc.EloCheck.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.duoc.EloCheck.dto.NombreEloDto;
import com.duoc.EloCheck.repository.UsuarioRepository;

import java.util.List;
import java.util.Arrays;

@Service
public class JuegoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<String> listarNombresJuegos() {
        // Implementación básica: devolver una lista de nombres de juegos de ejemplo
        return Arrays.asList("Juego1", "Juego2", "Juego3");
    }

    public List<NombreEloDto> getNombreConEloDto() {
        return usuarioRepository.findAll().stream()
                .map(l -> new NombreEloDto(
                        l.getNombre(),
                        l.getHardware().getNombreEquipo(),
                        l.getHardware().getElo()
                ))
                .toList();
    }
}