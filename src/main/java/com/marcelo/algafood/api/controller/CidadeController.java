package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.exception.ConstraintViolationException;
import com.marcelo.algafood.domain.exception.EstadoNaoEncontradoException;
import com.marcelo.algafood.domain.exception.NegocioException;
import com.marcelo.algafood.domain.exception.ResourceAlreadyExistsException;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.repository.CidadeRepository;
import com.marcelo.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cadastroCidade.findById(cidadeId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save(@RequestBody @Valid Cidade cidade) {

        try {
            return cadastroCidade.save(cidade);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResourceAlreadyExistsException(e.getMessage());
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        } catch (DataIntegrityViolationException ex) {
            Cidade response = cidadeRepository.isExists(cidade.getNome());
            throw new ConstraintViolationException(
                    String.format("CIDADE " + response.getNome() + " JÁ CADASTRADA. "), null, ex.getCause().toString());
        }
    }

    @PutMapping("/update/{cidadeId}")
    public Cidade update(@PathVariable Long cidadeId,
                         @RequestBody @Valid Cidade cidade) {
        try {
            Cidade cidadeAtual = cadastroCidade.findById(cidadeId);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cadastroCidade.save(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cidadeId) {
        cadastroCidade.delete(cidadeId);
    }

}
