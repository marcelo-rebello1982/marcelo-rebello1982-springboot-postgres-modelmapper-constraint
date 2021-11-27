package com.marcelo.algafood.domain.service;


import com.marcelo.algafood.api.controller.CidadeController;
import com.marcelo.algafood.api.model.response.ColaboradorModel;
import com.marcelo.algafood.domain.exception.ColaboradorEmUsoException;
import com.marcelo.algafood.domain.exception.ColaboradorNaoEncontradoException;
import com.marcelo.algafood.domain.exception.ConstraintViolationException;
import com.marcelo.algafood.domain.exception.ResourceAlreadyExistsException;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.repository.CafeRepository;
import com.marcelo.algafood.domain.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// https://prateek-ashtikar512.medium.com/dynamic-spring-projection-dbb4d360adf7
// testasr interFACE

@Service
public class CadastroColaboradorService {

    private static final String MSG_COLABORADOR_EM_USO
            = "Colaborador de código %d não pode ser removido,  xxx acertar";

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private CidadeController cidadeController;

    @Autowired
    private CadastroCafeService cafeService;

    public Page<Colaborador> findAll(Pageable pageable) {
        return colaboradorRepository.findAll(pageable);
    }


    public Colaborador findById(Long colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(colaboradorId));
    }

    public Colaborador save(Colaborador colaborador) {

        try {
            for (Cafe cafe : colaborador.getCafeList()) {
                List<Cafe> cafesList = cafeRepository.findAll()
                        .stream().filter(cafes -> cafes.getTipo()
                                .equals(cafe.getTipo()))
                        .collect(Collectors.toList());
                if (cafesList.isEmpty())
                    return colaboradorRepository.save(colaborador);
                throw new ResourceAlreadyExistsException(" TIPO " + cafe.getTipo() + " JÁ CADASTRADO ");
            }
        } catch (DataIntegrityViolationException ex) {
            Colaborador duplicated = colaboradorRepository.isExists(colaborador.getCpfcnpj());
            throw new ConstraintViolationException(
                    String.format("CPF " + colaborador.getCpfcnpj() + " JÁ CADASTRADO PARA : " + duplicated.getNome()), null, ex.getCause().toString());
        }
        return null;
    }

    @Transactional
    public void delete(Long colaboradorId) {
        try {
            colaboradorRepository.deleteById(colaboradorId);
            colaboradorRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ColaboradorNaoEncontradoException(colaboradorId);

        } catch (DataIntegrityViolationException e) {
            throw new ColaboradorEmUsoException(
                    String.format(MSG_COLABORADOR_EM_USO, colaboradorId));
        }
    }
}