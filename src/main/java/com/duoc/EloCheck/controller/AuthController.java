package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.AuthRequest;
import com.duoc.EloCheck.dto.AuthResponse;
import com.duoc.EloCheck.model.Usuario_seguridad;
import com.duoc.EloCheck.repository.Usuario_seguridadRepository;
import com.duoc.EloCheck.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticación.
 *
 * Endpoints públicos (no requieren JWT):
 *  POST /api/v1/auth/register  → registra un nuevo usuario con rol ROLE_USER
 *  POST /api/v1/auth/login     → autentica credenciales y devuelve un JWT
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Usuario_seguridadRepository usuario_seguridadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario con rol USER.
     * La contraseña se almacena encriptada con BCrypt.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (usuario_seguridadRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        Usuario_seguridad usuario = new Usuario_seguridad();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole("ROLE_USER");
        usuario_seguridadRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    /**
     * Autentica al usuario y devuelve un JWT válido por 24 horas.
     * El token debe enviarse en el header Authorization de los siguientes requests:
     *   Authorization: Bearer <token>
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            String token = jwtUtil.generateToken(request.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}