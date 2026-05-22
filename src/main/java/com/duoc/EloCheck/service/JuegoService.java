package com.duoc.EloCheck.service;

import com.duoc.EloCheck.model.Juego;
import com.duoc.EloCheck.repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoService {

    //Constructor en vez de @Autowired
    private final JuegoRepository juegoRepository;

    public JuegoService(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    public List<Juego> obtenerTodos() {
        System.out.println("[SERVICE] Obteniendo todos los juegos");
        List<Juego> juegos = juegoRepository.findAll();
        System.out.println("[SERVICE] Total de juegos: " + juegos.size());
        return juegos;
    }

    //Consulta la BD real
    public List<String> listarNombresJuegos() {
        return juegoRepository.findAll()
                .stream()
                .map(Juego::getJuegoNombre)
                .toList();
    }

    //buscar por ID
    public Juego buscarPorId(Integer id) {
        return juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado con id: " + id));
    }

    //guardar
    public Juego guardar(Juego juego) {
        return juegoRepository.save(juego);
    }

    //eliminar
    public void eliminar(Integer id) {
        if (!juegoRepository.existsById(id)) {
            throw new RuntimeException("No existe un juego con id: " + id);
        }
        juegoRepository.deleteById(id);
    }
}