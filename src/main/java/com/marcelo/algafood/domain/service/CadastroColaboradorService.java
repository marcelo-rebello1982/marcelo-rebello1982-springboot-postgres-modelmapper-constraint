package com.marcelo.algafood.domain.service;


import com.marcelo.algafood.domain.exception.ColaboradorEmUsoException;
import com.marcelo.algafood.domain.exception.ColaboradorNaoEncontradoException;
import com.marcelo.algafood.domain.exception.ConstraintViolationException;
import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.repository.CafeRepository;
import com.marcelo.algafood.domain.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroColaboradorService {

    private static final String MSG_COLABORADOR_EM_USO
            = "Colaborador de código %d não pode ser removido,  xxx acertar";

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private CadastroCafeService cafeService;

    public Colaborador save(Colaborador colaborador) {
        try {
            return colaboradorRepository.save(colaborador);
        } catch (DataIntegrityViolationException ex) {
            Colaborador response = colaboradorRepository.isExists(colaborador.getCpfcnpj());
            throw new ConstraintViolationException(
                    String.format("CPF :  JÁ CADASTRADO PARA : " + response.getNome()), null , ex.getCause().toString());
        }
    }

    public Colaborador findById(Long colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(colaboradorId));
    }

    public void delete(Long colaboradorId) {
        try {
            colaboradorRepository.deleteById(colaboradorId);
        } catch (EmptyResultDataAccessException e) {
            throw new ColaboradorNaoEncontradoException(colaboradorId);

        } catch (DataIntegrityViolationException e) {
            throw new ColaboradorEmUsoException(
                    String.format(MSG_COLABORADOR_EM_USO, colaboradorId));
        }
    }

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }
}