package com.duoc.EloCheck.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "juegos")
public class Juego {

    public Juego(int id2, String juegoNombre2, Equipo equipoMinimo, Equipo equipoRecomendado) {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String juegoNombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisito_id")
    private Requisito requisitoMinimo; 

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisito_recomendado_id")
    private Requisito requisitoRecomendado;
}


