package com.duoc.EloCheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.EloCheck.model.Requisito;
import com.duoc.EloCheck.repository.RequisitoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RequisitoService {

    @Autowired
    private RequisitoRepository requisitoRepository;

    public List<Requisito> obtenerTodos() {
        System.out.println("[SERVICE] Obteniendo todos los requisitos");
        List<Requisito> requisitos = requisitoRepository.findAll();
        System.out.println("[SERVICE] Total de requisitos: " + requisitos.size());
        return requisitos;
    }

    public Requisito obtenerPorId(Integer id) {
        System.out.println("[SERVICE] Buscando requisito con id: " + id);
        Requisito requisito = requisitoRepository.findById(id).orElse(null);
        if (requisito == null) {
            System.out.println("[SERVICE] Requisito no encontrado");
        } else {
            System.out.println("[SERVICE] Requisito encontrado con Elo: " + requisito.getElo());
        }
        return requisito;
    }

    public Requisito crear(Requisito requisito) {
        System.out.println("[SERVICE] Creando requisito con Elo: " + requisito.getElo());
        Requisito guardado = requisitoRepository.save(requisito);
        System.out.println("[SERVICE] Requisito guardado con id: " + guardado.getId());
        return guardado;
    }

    public Requisito actualizar(Integer id, Requisito requisito) {
        System.out.println("[SERVICE] Actualizando requisito con id: " + id);
        Optional<Requisito> existente = requisitoRepository.findById(id);
        if (existente.isPresent()) {
            Requisito req = existente.get();
            req.setRam(requisito.getRam());
            req.setProcesador(requisito.getProcesador());
            req.setGrafica(requisito.getGrafica());
            req.setEspacio(requisito.getEspacio());
            req.setElo(requisito.getElo());
            Requisito actualizado = requisitoRepository.save(req);
            System.out.println("[SERVICE] Requisito actualizado correctamente");
            return actualizado;
        }
        System.out.println("[SERVICE] Requisito no encontrado para actualizar");
        return null;
    }

    public void eliminar(Integer id) {
        System.out.println("[SERVICE] Eliminando requisito con id: " + id);
        if (requisitoRepository.existsById(id)) {
            requisitoRepository.deleteById(id);
            System.out.println("[SERVICE] Requisito eliminado correctamente");
        } else {
            System.out.println("[SERVICE] Requisito no encontrado para eliminar");
        }
    }
}