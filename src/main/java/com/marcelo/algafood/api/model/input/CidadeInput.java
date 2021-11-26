package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CidadeInput extends AbstractEntity<Long> {

    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EstadoInput estadoInput;
}