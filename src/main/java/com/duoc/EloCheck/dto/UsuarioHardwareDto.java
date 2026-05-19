package com.duoc.EloCheck.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
// DTO: lo que la API devuelve cuando alguien consulta un usuario.
// No exponemos la clave ni el id directamente — buena práctica de seguridad.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioHardwareDto {
 
    private String nombre;          // Nombre del usuario
    private String email;           // Email del usuario
 
    // Datos del equipo asociado
    private String nombreEquipo;    // Ej: "PC Gamer Pro"
    private Integer ram;            // GB de RAM
    private String grafica;         // GPU del equipo
    private String procesador;      // CPU del equipo
    private String elo;             // Nivel ELO calculado según hardware
}
 
