package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.model.Requisito;
import com.duoc.EloCheck.model.Juego;
import com.duoc.EloCheck.service.JuegoService;

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
class JuegoControllerTest {

    @Mock
    private JuegoService juegoService;

    @InjectMocks
    private JuegoController juegoController;

    @Test
    void crearJuego_retorna201_cuandoDatosSonValidos() {

        System.out.println("\n===== INICIANDO TEST: crearJuego_retorna201_cuandoDatosSonValidos =====");
        
        // Vamos a verificar que el método crear del controlador funciona correctamente
        // Para ello crearemos un usuario con un equipo válido y simularemos el comportamiento del servicio
        Requisito requisitoMinimo = new Requisito(1, 16, "GTX 1660", "Ryzen 5 3500", 70, "Oro");
        Requisito requisitoRecomendado = new Requisito(1, 16, "RTX 2060 Super", "Ryzen 5 5500", 70, "Diamante");
        Juego juego = new Juego(1, "Resident Evil Requiem", requisitoMinimo, requisitoRecomendado);

        System.out.println("[TEST] Datos preparados:");
        System.out.println("[TEST]   - Nombre del juego: " + juego.getJuegoNombre());
        System.out.println("[TEST] Datos de Requisitos Minimos:");
        System.out.println("[TEST]   - RAM: " + requisitoMinimo.getRam() + "GB");
        System.out.println("[TEST]   - GPU: " + requisitoMinimo.getGrafica());
        System.out.println("[TEST]   - CPU: " + requisitoMinimo.getProcesador());
        System.out.println("[TEST]   - Espacio: " + requisitoMinimo.getEspacio() + "GB");
        System.out.println("[TEST]   - Elo: " + requisitoMinimo.getElo());
        System.out.println("[TEST] Datos de Requisitos Recomendados:");
        System.out.println("[TEST]   - RAM: " + requisitoRecomendado.getRam() + "GB");
        System.out.println("[TEST]   - GPU: " + requisitoRecomendado.getGrafica());
        System.out.println("[TEST]   - CPU: " + requisitoRecomendado.getProcesador());
        System.out.println("[TEST]   - Espacio: " + requisitoRecomendado.getEspacio() + "GB");
        System.out.println("[TEST]   - Elo: " + requisitoRecomendado.getElo());

        // Simulamos el comportamiento del servicio (Mock)
        // Cuando el controlador invoque guardar(), Mockito devolverá el juego sin tocar la base de datos.
        when(juegoService.guardar(juego)).thenReturn(juego);

        System.out.println("[TEST] Mock del servicio configurado");
        System.out.println("[TEST] Cuando se invoque juegoService.guardar(), retornará el juego");

        // Llamamos al método del controlador que queremos probar.
        System.out.println("[TEST] Ejecutando: juegoController.crear(juego)");
        ResponseEntity<Juego> respuesta = juegoController.crear(juego);

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
        assertEquals("Resident Evil Requiem", body.getJuegoNombre(), "El nombre del juego no coincide");
        System.out.println("[TEST] Assertion 4: Nombre del juego es correcto");
        
        System.out.println("\n[TEST] TODAS LAS PRUEBAS PASARON EXITOSAMENTE");
        System.out.println("===== FIN DEL TEST =====\n");
    }
}