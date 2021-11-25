package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

// @GroupSequenceProvider(ColaboradorGroupSequenceProvider.class)
@Setter
@Getter
public class EnderecoInput extends AbstractEntity<Long> {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_cidade_id")
    private CidadeInput cidadeInput;

}
