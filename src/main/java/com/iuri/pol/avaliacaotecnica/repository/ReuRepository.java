package com.iuri.pol.avaliacaotecnica.repository;

import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Reu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReuRepository extends JpaRepository<Reu, Long>{
    
    public void deleteByIdProcesso(long idProcesso);

    public Optional<List<Reu>> findAllByIdProcesso(long idProcesso);

}
