package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.model.Compromisso;
import com.marcelo.algafood.domain.repository.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompromissoService {


    @Autowired
    private CompromissoRepository compromissoRepository;

    public Optional<Compromisso> findById(Long id) {
        return compromissoRepository.findById(id);
    }


    public Compromisso save(Compromisso compromisso) {
        return compromissoRepository.save(compromisso);
    }

    public List<Compromisso> findAll() {
        return compromissoRepository.findAll();
    }

    public Page<Compromisso> findAll(Pageable pageable) {
        return compromissoRepository.findAll(pageable);
    }

    public List<Compromisso> findByNomeDescricao(String nome, String descricao) {
        return compromissoRepository.findByNomeOrDescricao(nome, descricao);
    }

    public List<Compromisso> findByDataCompromissoOrCadastro(OffsetDateTime dataCompromisso, OffsetDateTime dataCadastro) {
        return compromissoRepository.findByDataCompromissoOrDataCadastro(dataCompromisso, dataCadastro);
    }


//    public List<Compromisso> findByNomeOrDescricao(String nome, String descricao) {
//        return compromissoRepository.ffindBynomeDoResponsavelOrdescricaoDoCompromisso(nome, descricao);
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long Id) {
        compromissoRepository.delete(compromissoRepository.findById(Id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("Não %s encontrado id %s !", Id), 1)));
        return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
    }
}
