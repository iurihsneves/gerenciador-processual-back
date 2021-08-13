package com.iuri.pol.avaliacaotecnica.controller;

import java.util.List;
import java.util.Optional;

import com.iuri.pol.avaliacaotecnica.modelo.Reu;
import com.iuri.pol.avaliacaotecnica.repository.ReuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/reu")
public class ReuController {

    @Autowired
    ReuRepository repository;

    @GetMapping("/listar-reu/{idProcesso}")
    public ResponseEntity<Optional<List<Reu>>> listaReu(@PathVariable(value="idProcesso") long idProcesso) {
        return ResponseEntity.ok(repository.findAllByIdProcesso(idProcesso));
    }

    @PostMapping("/reu")
    public ResponseEntity<Reu> cadastrarReu(@RequestBody Reu reu) {
        return ResponseEntity.ok(repository.save(reu));
    }


    @RequestMapping(value="/excluir-reu/{id}", method=RequestMethod.DELETE)
    public void excluiReu(@PathVariable long id) {
        repository.deleteById(id);
    }
    
}
