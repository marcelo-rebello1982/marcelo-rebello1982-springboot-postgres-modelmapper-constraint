package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.exception.EstadoNaoEncontradoException;
import com.marcelo.algafood.domain.exception.NegocioException;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.repository.CidadeRepository;
import com.marcelo.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade save(@RequestBody @Valid Cidade cidade) {
		try {
			return cadastroCidade.save(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
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
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cidadeId) {
		cadastroCidade.delete(cidadeId);
	}
	
}
