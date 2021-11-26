package com.marcelo.algafood.api.model;

import com.marcelo.algafood.domain.model.Cidade;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

@Getter
@Setter
public class EnderecoModel {

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private CidadeResumoModel cidade;
}
