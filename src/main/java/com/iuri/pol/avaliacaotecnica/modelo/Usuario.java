package com.iuri.pol.avaliacaotecnica.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean admin;

    public Usuario() {

    }

    public Usuario(int id, String password, String nomeCompleto, String email) {

        this.id = id;
        this.password = password;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        
    }

}
