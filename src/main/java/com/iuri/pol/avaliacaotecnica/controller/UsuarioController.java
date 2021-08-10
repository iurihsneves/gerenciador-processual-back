package com.iuri.pol.avaliacaotecnica.controller;

import java.util.List;
import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Usuario;
import com.iuri.pol.avaliacaotecnica.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(repository.save(usuario));
    }

    @GetMapping("/validar-senha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String email,
                                                @RequestParam String senha) {
        
    Optional<Usuario> optUsuario = repository.findByEmail(email);
        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = false;
        valid = encoder.matches(senha, optUsuario.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        
        return ResponseEntity.status(status).body(valid);

    }   
    
}
