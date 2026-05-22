package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.NombreEloDto;
import com.duoc.EloCheck.dto.UsuarioHardwareDto;
import com.duoc.EloCheck.model.Usuario;
import com.duoc.EloCheck.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /api/usuarios → lista todos los usuarios con su hardware
    @GetMapping
    public ResponseEntity<List<UsuarioHardwareDto>> listarTodos() {
        System.out.println("[CONTROLLER] GET /api/usuarios - Iniciando");
        List<UsuarioHardwareDto> usuarios = usuarioService.listarUsuariosConHardware();
        System.out.println("[CONTROLLER] Usuarios obtenidos: " + usuarios.size());
        return ResponseEntity.ok(usuarios);
    }

    // GET /api/usuarios/{id} → trae un usuario específico con su equipo
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioHardwareDto> buscarPorId(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] GET /api/usuarios/" + id + " - Iniciando");
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // POST /api/usuarios → registra un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        System.out.println("[CONTROLLER] POST /api/usuarios - Iniciando");
        return ResponseEntity.status(201).body(usuarioService.guardar(usuario));
    }

    // DELETE /api/usuarios/{id} → elimina un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        System.out.println("[CONTROLLER] DELETE /api/usuarios/" + id + " - Iniciando");
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/con-elo")
    public ResponseEntity<List<NombreEloDto>> nombrePorElo() {
        System.out.println("[CONTROLLER] GET /api/usuarios/con-elo - Iniciando");
        return ResponseEntity.ok(usuarioService.getNombreConEloDto());
    }
}