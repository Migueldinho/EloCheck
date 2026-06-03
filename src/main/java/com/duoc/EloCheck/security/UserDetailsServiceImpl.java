package com.duoc.EloCheck.security;

import com.duoc.EloCheck.model.Usuario_seguridad;
import com.duoc.EloCheck.repository.Usuario_seguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de UserDetailsService requerida por Spring Security.
 *
 * Spring la llama internamente durante el proceso de autenticación
 * (AuthenticationManager.authenticate) para cargar al usuario desde la BD
 * y comparar su contraseña encriptada con la recibida en el login.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private Usuario_seguridadRepository usuario_seguridadRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario_seguridad usuario = usuario_seguridadRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority(usuario.getRole()))
        );
    }
}