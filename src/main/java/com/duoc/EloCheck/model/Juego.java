package com.duoc.EloCheck.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String juegoNombre;

    @NotNull
    private Integer ram;

    @NotBlank
    private String grafica;

    @NotBlank
    private String procesador;

    @NotNull
    private Integer espacio;

    @NotBlank
    private String elo;
}