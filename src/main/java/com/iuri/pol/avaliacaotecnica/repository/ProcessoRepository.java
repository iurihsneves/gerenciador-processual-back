package com.iuri.pol.avaliacaotecnica.repository;

import java.util.List;
import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Processo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {

    public Optional<List<Processo>> findAllByNmReu(String nmReu);

    public Optional<List<Processo>> findByNrProcesso(long nrProcesso);
    
}
