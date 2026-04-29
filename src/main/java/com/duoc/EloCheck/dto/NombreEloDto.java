package com.duoc.EloCheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NombreEloDto {

    private String nombre;
    private String nombreEquipo;
    private String elo;
}
