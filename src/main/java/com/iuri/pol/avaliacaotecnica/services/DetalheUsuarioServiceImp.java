package com.iuri.pol.avaliacaotecnica.services;

import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.data.DetalheUsuarioData;
import com.iuri.pol.avaliacaotecnica.modelo.Usuario;
import com.iuri.pol.avaliacaotecnica.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetalheUsuarioServiceImp implements UserDetailsService {

    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImp(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repository.findByEmail(email);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("O login [" + email +"] não está cadastrado");
        }
        return new DetalheUsuarioData(usuario);
    }

    
    
}
