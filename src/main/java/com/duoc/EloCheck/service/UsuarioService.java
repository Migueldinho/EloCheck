package com.duoc.EloCheck.service;
 
import com.duoc.EloCheck.dto.UsuarioHardwareDto;
import com.duoc.EloCheck.model.Usuario;
import com.duoc.EloCheck.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class UsuarioService {
 
    private final UsuarioRepository usuarioRepository;
 
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
 
    // Devuelve todos los usuarios con info de su equipo (sin exponer la clave)
    public List<UsuarioHardwareDto> listarUsuariosConHardware() {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getHardware() != null) // solo usuarios con equipo registrado
                .map(u -> new UsuarioHardwareDto(
                        u.getNombre(),
                        u.getEmail(),
                        u.getHardware().getNombreEquipo(),
                        u.getHardware().getRam(),
                        u.getHardware().getGrafica(),
                        u.getHardware().getProcesador(),
                        u.getHardware().getElo()
                ))
                .toList();
    }
 
    // Busca un usuario por ID y lo devuelve como DTO
    public UsuarioHardwareDto buscarPorId(Integer id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
 
        if (u.getHardware() == null) {
            throw new RuntimeException("El usuario no tiene equipo registrado");
        }
 
        return new UsuarioHardwareDto(
                u.getNombre(),
                u.getEmail(),
                u.getHardware().getNombreEquipo(),
                u.getHardware().getRam(),
                u.getHardware().getGrafica(),
                u.getHardware().getProcesador(),
                u.getHardware().getElo()
        );
    }
 
    // Registra un nuevo usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
 
    // Elimina un usuario por ID
    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No existe un usuario con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}