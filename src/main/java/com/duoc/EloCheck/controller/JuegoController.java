package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.NombreEloDto;
import com.duoc.EloCheck.model.Juego;
import com.duoc.EloCheck.service.JuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    //Solo constructor, sin @Autowired
    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<String>> listarNombres() {
        return ResponseEntity.ok(juegoService.listarNombresJuegos());
    }

    @GetMapping("/con-elo")
    public ResponseEntity<List<NombreEloDto>> nombrePorElo() {
        return ResponseEntity.ok(juegoService.getNombreConEloDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(juegoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Juego> crear(@RequestBody Juego juego) {
        return ResponseEntity.status(201).body(juegoService.guardar(juego));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        juegoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}