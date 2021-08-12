package com.iuri.pol.avaliacaotecnica.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_reu")
public class Reu{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nomeCompleto;

    private String telefone;

    private long nrProcesso;

    public Reu(int id, String nomeCompleto, String telefone) {

        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }

}
