package com.duoc.EloCheck.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
/*import jakarta.validation.constraints.NotNull;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String clave;

    @NotBlank
    private String email;

}
