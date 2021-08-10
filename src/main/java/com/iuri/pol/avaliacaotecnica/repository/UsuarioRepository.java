package com.iuri.pol.avaliacaotecnica.repository;


import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    public Optional<Usuario> findByEmail(String email);
    
}
