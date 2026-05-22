package com.duoc.EloCheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.EloCheck.model.Equipo;
import com.duoc.EloCheck.repository.EquipoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> obtenerTodos() {
        System.out.println("[SERVICE] Obteniendo todos los equipos");
        List<Equipo> equipos = equipoRepository.findAll();
        System.out.println("[SERVICE] Total de equipos: " + equipos.size());
        return equipos;
    }

    public Equipo obtenerPorId(Integer id) {
        System.out.println("[SERVICE] Buscando equipo con id: " + id);
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        if (equipo == null) {
            System.out.println("[SERVICE] Equipo no encontrado");
        } else {
            System.out.println("[SERVICE] Equipo encontrado: " + equipo.getNombreEquipo());
        }
        return equipo;
    }

    public Equipo crear(Equipo equipo) {
        System.out.println("[SERVICE] Creando equipo: " + equipo.getNombreEquipo());
        Equipo guardado = equipoRepository.save(equipo);
        System.out.println("[SERVICE] Equipo guardado con id: " + guardado.getId());
        return guardado;
    }

    public Equipo actualizar(Integer id, Equipo equipo) {
        System.out.println("[SERVICE] Actualizando equipo con id: " + id);
        Optional<Equipo> existente = equipoRepository.findById(id);
        if (existente.isPresent()) {
            Equipo eq = existente.get();
            eq.setNombreEquipo(equipo.getNombreEquipo());
            eq.setProcesador(equipo.getProcesador());
            eq.setRam(equipo.getRam());
            eq.setGrafica(equipo.getGrafica());
            Equipo actualizado = equipoRepository.save(eq);
            System.out.println("[SERVICE] Equipo actualizado correctamente");
            return actualizado;
        }
        System.out.println("[SERVICE] Equipo no encontrado para actualizar");
        return null;
    }

    public void eliminar(Integer id) {
        System.out.println("[SERVICE] Eliminando equipo con id: " + id);
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            System.out.println("[SERVICE] Equipo eliminado correctamente");
        } else {
            System.out.println("[SERVICE] Equipo no encontrado para eliminar");
        }
    }
}