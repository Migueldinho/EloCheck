package com.duoc.EloCheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.EloCheck.model.Equipo;
import com.duoc.EloCheck.service.EquipoService;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodos() {
        System.out.println("[CONTROLLER] GET /api/equipos - Iniciando");
        List<Equipo> equipos = equipoService.obtenerTodos();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerPorId(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] GET /api/equipos/" + id);
        Equipo equipo = equipoService.obtenerPorId(id);
        return ResponseEntity.ok(equipo);
    }

    @PostMapping
    public ResponseEntity<Equipo> crear(@RequestBody Equipo equipo) {
        System.out.println("[CONTROLLER] POST /api/equipos - Creando: " + equipo.getNombreEquipo());
        Equipo creado = equipoService.crear(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(@PathVariable Integer id, @RequestBody Equipo equipo) {
        System.out.println("[CONTROLLER] PUT /api/equipos/" + id);
        Equipo actualizado = equipoService.actualizar(id, equipo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] DELETE /api/equipos/" + id);
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}