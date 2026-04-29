package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.NombreEloDto;
import com.duoc.EloCheck.service.JuegoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/juegos")

public class JuegoController {
    
    @Autowired
    private JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<String>> listarNombres(){
        return ResponseEntity.ok(juegoService.listarNombresJuegos()); /* CODIGO 200*/ 
        /*returnResponseEntity.badRequest().body("datos invalidos"); CODIGO 400*/
    }

    @GetMapping("/con-elo")
    public ResponseEntity<List<NombreEloDto>> nombrePorElo() {
        return ResponseEntity.ok(juegoService.getNombreConEloDto()); /* CODIGO 200*/
    }
    
}
