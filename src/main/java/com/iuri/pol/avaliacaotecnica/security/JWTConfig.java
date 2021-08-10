package com.iuri.pol.avaliacaotecnica.security;

import com.iuri.pol.avaliacaotecnica.services.DetalheUsuarioServiceImp;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class JWTConfig extends WebSecurityConfigurerAdapter {
    
    private final DetalheUsuarioServiceImp usuarioService;

    private final PasswordEncoder passwordEncoder;

    public JWTConfig(DetalheUsuarioServiceImp usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    //deixei desabilitado, uma vez que este projeto não é para fins de produção
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/usuario/cadastrar-usuario").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAutenticarFilter(authenticationManager()))
            .addFilter(new JWTValidarFilter((authenticationManager())))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean 
    public WebMvcConfigurer getCorsConfiguration() {
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
            }
        };
    }

}
