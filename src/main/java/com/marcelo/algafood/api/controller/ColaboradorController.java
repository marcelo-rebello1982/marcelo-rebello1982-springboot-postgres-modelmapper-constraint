package com.marcelo.algafood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.algafood.api.assembler.ColaboradorInputDisassembler;
import com.marcelo.algafood.api.assembler.ColaboradorModelMapper;
import com.marcelo.algafood.api.model.ColaboradorModel;
import com.marcelo.algafood.api.model.input.ColaboradorInput;
import com.marcelo.algafood.domain.exception.*;
import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.model.Restaurante;
import com.marcelo.algafood.domain.service.CadastroColaboradorService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

    @Autowired
    private CadastroColaboradorService colaboradorService;

    @Autowired
    private ColaboradorModelMapper colaboradorModelMapper;

    @Autowired
    private ColaboradorInputDisassembler colaboradorInputDisassembler;


    @GetMapping("/findAll")
    public List<ColaboradorModel> findAll() {
        return colaboradorModelMapper
                .toCollectionModel(colaboradorService
                        .findAll());
    }

    @GetMapping("/findByID/{colaboradorId}")
    public ColaboradorModel findById(@PathVariable Long colaboradorId) {
        Colaborador colaborador = colaboradorService.findById(colaboradorId);
        return colaboradorModelMapper.toModel(colaborador);

    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ColaboradorModel save(@RequestBody @Valid ColaboradorInput colaboradorInput) {

        try {
            Colaborador colaborador = colaboradorInputDisassembler.toDomainObject(colaboradorInput);
            return colaboradorModelMapper.toModel(colaboradorService.save(colaborador));
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintViolationException(e.getMessage(), null, e.getLocalizedMessage());
        } catch (CafeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResourceAlreadyExistsException(e.getMessage());
        }
    }

    @PutMapping("/update/{colaboradorId}")
    public ColaboradorModel update(@PathVariable Long colaboradorId, @RequestBody @Valid ColaboradorInput colaboradorInput) {

        try {
            Colaborador colaboradorAtual = colaboradorService.findById(colaboradorId);
            colaboradorInputDisassembler.copyToDomainObject(colaboradorInput, colaboradorAtual);
            colaboradorAtual = colaboradorService.save(colaboradorAtual);
            return colaboradorModelMapper.toModel(colaboradorService.save(colaboradorAtual));
        } catch (ColaboradorNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete/{colaboradorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long colaboradorId) {
        colaboradorService.delete(colaboradorId);
    }

    private void merge(Map<String, Object> dadosOrigem, Colaborador colaboradorDestino,
                       HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Colaborador colaboradorOrigem = objectMapper.convertValue(dadosOrigem, Colaborador.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, colaboradorOrigem);
                ReflectionUtils.setField(field, colaboradorDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }
}
