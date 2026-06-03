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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotBlank
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Equipo")
    private Equipo hardware;

    /**
     * Rol del usuario. Valores esperados: "ROLE_USER" o "ROLE_ADMIN".
     * Spring Security usa el prefijo ROLE_ para los métodos hasRole().
     */
    @Column(nullable = false)
    private String role;

}
