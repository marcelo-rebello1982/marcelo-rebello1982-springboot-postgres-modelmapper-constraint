package com.marcelo.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private CidadeResumoModel cidade;
}
