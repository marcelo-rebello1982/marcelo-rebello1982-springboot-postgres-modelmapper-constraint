package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Getter
@Setter
public class Estado extends AbstractEntity<Long>{

	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
}