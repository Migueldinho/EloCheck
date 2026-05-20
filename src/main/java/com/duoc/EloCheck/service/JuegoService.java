package com.duoc.EloCheck.service;

import com.duoc.EloCheck.dto.NombreEloDto;
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