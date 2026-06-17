package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.model.Usuario;
import com.duoc.EloCheck.model.Equipo;
import com.duoc.EloCheck.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void crearUsuario_retorna201_cuandoDatosSonValidos() {

        System.out.println("\n===== INICIANDO TEST: crearUsuario_retorna201_cuandoDatosSonValidos =====");
        
        // Vamos a verificar que el método crear del controlador funciona correctamente
        // Para ello crearemos un usuario con un equipo válido y simularemos el comportamiento del servicio
        Equipo equipo = new Equipo(1, "Pc juegos", 32, "RTX 3080 TI", "Ryzen 9 9950x", "Maestro");
        Usuario usuario = new Usuario(1, "Gabriel García Márquez", "sebasd", "gagam@duoc.cl", equipo);

        System.out.println("[TEST] Datos preparados:");
        System.out.println("[TEST]   - Usuario: " + usuario.getNombre());
        System.out.println("[TEST]   - Email: " + usuario.getEmail());
        System.out.println("[TEST]   - Equipo: " + equipo.getNombreEquipo());
        System.out.println("[TEST]   - RAM: " + equipo.getRam() + "GB");
        System.out.println("[TEST]   - GPU: " + equipo.getGrafica());
        System.out.println("[TEST]   - CPU: " + equipo.getProcesador());
        System.out.println("[TEST]   - Elo: " + equipo.getElo());

        // Simulamos el comportamiento del servicio (Mock)
        // Cuando el controlador invoque guardar(), Mockito devolverá el usuario sin tocar la base de datos.
        when(usuarioService.guardar(usuario)).thenReturn(usuario);

        System.out.println("[TEST] Mock del servicio configurado");
        System.out.println("[TEST] Cuando se invoque usuarioService.guardar(), retornará el usuario");

        // Llamamos al método del controlador que queremos probar.
        System.out.println("[TEST] Ejecutando: usuarioController.crear(usuario)");
        ResponseEntity<Usuario> respuesta = usuarioController.crear(usuario);

        System.out.println("[TEST] Respuesta recibida del controlador");

        // Para que el test sea completo, verificamos varios aspectos de la respuesta:

        // 1) La respuesta no debe ser nula.
        System.out.println("[TEST] Iniciando validaciones (Assertions)...");
        
        assertNotNull(respuesta, "La respuesta no debería ser nula");
        System.out.println("[TEST] Assertion 1: Respuesta no es nula");

        // 2) El estado HTTP esperado al crear un recurso es 201 (CREATED).
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode(), "El estado HTTP debe ser 201 (CREATED)");
        System.out.println("[TEST] Assertion 2: Status HTTP es 201 CREATED");

        // 3) El cuerpo de la respuesta debe existir.
        var body = respuesta.getBody();
        assertNotNull(body, "El cuerpo de la respuesta no debería estar vacío");
        System.out.println("[TEST] Assertion 3: Body de la respuesta no es nulo");
        

        // 4) Validamos que el nombre del usuario devuelto coincida con el que enviamos
        assertEquals("Gabriel García Márquez", body.getNombre(), "El nombre del usuario no coincide");
        System.out.println("[TEST] Assertion 4: Nombre del usuario es correcto");
        
        System.out.println("\n[TEST] TODAS LAS PRUEBAS PASARON EXITOSAMENTE");
        System.out.println("===== FIN DEL TEST =====\n");
    }
}