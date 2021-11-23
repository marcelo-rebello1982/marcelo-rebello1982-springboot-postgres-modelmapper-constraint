package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.model.Estado;
import com.marcelo.algafood.domain.repository.EstadoRepository;
import com.marcelo.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado findById(@PathVariable Long estadoId) {
		return cadastroEstado.findById(estadoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody @Valid Estado estado) {
		return cadastroEstado.save(estado);
	}
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId,
			@RequestBody @Valid Estado estado) {
		Estado estadoAtual = cadastroEstado.findById(estadoId);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return cadastroEstado.save(estadoAtual);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.remover(estadoId);
	}
	
}
