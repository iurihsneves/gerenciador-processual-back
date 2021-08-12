package com.iuri.pol.avaliacaotecnica.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuri.pol.avaliacaotecnica.data.DetalheUsuarioData;
import com.iuri.pol.avaliacaotecnica.modelo.Usuario;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter{

    static final int EXPIRACAO_TOKEN = 1800_000;

    static final String SENHA_TOKEN = "7af37a13-3c5c-4f0a-aea3-405d72f72918";

    final String HEADER_STRING = "Authorization";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        
        try {
            Usuario usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), Usuario.class);

                    System.out.println(usuario);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest resquest,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        String token = JWT.create().withSubject(usuarioData.getUsername())
                                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRACAO_TOKEN))
                                    .sign(Algorithm.HMAC512(SENHA_TOKEN));
        response.addHeader(HEADER_STRING, "Bearer " + token);
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");
        response.getWriter().flush();
    }

}
