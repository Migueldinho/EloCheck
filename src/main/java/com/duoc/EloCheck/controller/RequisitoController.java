package com.duoc.EloCheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.EloCheck.model.Requisito;
import com.duoc.EloCheck.service.RequisitoService;
import java.util.List;

@RestController
@RequestMapping("/api/requisitos")
public class RequisitoController {

    @Autowired
    private RequisitoService requisitoService;

    @GetMapping
    public ResponseEntity<List<Requisito>> obtenerTodos() {
        System.out.println("[CONTROLLER] GET /api/requisitos - Iniciando");
        List<Requisito> requisitos = requisitoService.obtenerTodos();
        return ResponseEntity.ok(requisitos);
    }

    @PostMapping
    public ResponseEntity<Requisito> crear(@RequestBody Requisito requisito) {
        System.out.println("[CONTROLLER] POST /api/requisitos - Creando requisito");
        Requisito creado = requisitoService.crear(requisito);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisito> obtenerPorId(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] GET /api/requisitos/" + id);
        Requisito requisito = requisitoService.obtenerPorId(id);
        return ResponseEntity.ok(requisito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requisito> actualizar(@PathVariable Integer id, @RequestBody Requisito requisito) {
        System.out.println("[CONTROLLER] PUT /api/requisitos/" + id);
        Requisito actualizado = requisitoService.actualizar(id, requisito);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] DELETE /api/requisitos/" + id);
        requisitoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}