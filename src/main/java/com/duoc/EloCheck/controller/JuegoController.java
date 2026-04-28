package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.nombreEloDto;
import com.duoc.EloCheck.service.JuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/juegos")

public class JuegoController {
    
    private JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<String>> listarNombres(){
        return ResponseEntity.ok(juegoService.listarNombresJuegos());

    }

    @GetMapping("/con-nacionalidad")
    public ResponseEntity<List<nombreEloDto>> nombrePorElo() {
        return ResponseEntity.ok(juegoService.getNombreConEloDto());
    }
    
}
