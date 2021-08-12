package com.iuri.pol.avaliacaotecnica.resources;

import com.iuri.pol.avaliacaotecnica.modelo.Usuario;
import com.iuri.pol.avaliacaotecnica.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CriarUsuarioAdmin implements CommandLineRunner {
    
    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if(repository.findByEmail("admin@admin.com").isEmpty()) {
            System.out.println("Usuário admin não encontrado, iniciando procedimento de criação:");
            System.out.println("Email: admin@admin.com");
            System.out.println("Senha: '12345'");

            Usuario usuario = new Usuario();
            usuario.setEmail("admin@admin.com");
            usuario.setPassword(encoder.encode("12345"));
            usuario.setNomeCompleto("Admin");
            System.out.println(usuario);
            repository.save(usuario);
        } else {
            System.out.println("Usuário admin localizado - ignorando procedimento de criação.");
        }

        
        
    }

}
