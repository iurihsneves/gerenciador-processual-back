package com.iuri.pol.avaliacaotecnica.controller;

import java.util.List;
import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Processo;
import com.iuri.pol.avaliacaotecnica.repository.ProcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processo")
public class ProcessoControler {

    @Autowired
    ProcessoRepository processoRepository;

    @GetMapping("/processos")
    public List<Processo> listaProcessos() {
        return processoRepository.findAll();
    }
    
    @GetMapping("/processo/{nrProcesso}")
    public Optional<List<Processo>> findByNrProcesso(@PathVariable(value="nrProcesso") long nrProcesso) {
        return processoRepository.findByNrProcesso(nrProcesso);
    }

    @GetMapping("/processo/{nmReu}")
    public Optional<List<Processo>> findByNmReu(@PathVariable(value="nmReu") String nmReu) {
        return processoRepository.findAllByNmReu(nmReu);
    }

    @PostMapping("/processo")
    public Processo salvaProcesso(@RequestBody Processo processo) {
        return processoRepository.save(processo);
    }

    @DeleteMapping("/processo")
    public void deletaProcesso(@RequestBody Processo processo) {
        processoRepository.delete(processo);
    }

    @PutMapping("/processo")
    public Processo atualizaProcesso(@RequestBody Processo processo) {
        return processoRepository.save(processo);
    }
    
}
