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

        // 1. Preparación de los datos (Arrange)
        Equipo equipo = new Equipo(1, "Pc juegos", 32, "RTX 3080 TI", "Ryzen 9 9950x", "Maestro");
        Usuario usuario = new Usuario(1, "Gabriel García Márquez", "sebasd", "gagam@duoc.cl", equipo);

        // Simulamos el comportamiento del servicio (Mock)
        // Cuando el controlador invoque guardar(), Mockito devolverá el usuario sin tocar la base de datos.
        when(usuarioService.guardar(usuario)).thenReturn(usuario);

        // 2. Ejecución (Act)
        // Llamamos al método del controlador que queremos probar.
        ResponseEntity<Usuario> respuesta = usuarioController.crear(usuario);

        // 3. Verificaciones (Assert)
        assertNotNull(respuesta, "La respuesta no debería ser nula");
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode(), "El estado HTTP debe ser 201 (CREATED)");

        var body = respuesta.getBody();
        assertNotNull(body, "El cuerpo de la respuesta no debería estar vacío");
        
        // Validamos que el nombre del usuario devuelto coincida con el que enviamos
        assertEquals("Gabriel García Márquez", body.getNombre(), "El nombre del usuario no coincide");
    }
}