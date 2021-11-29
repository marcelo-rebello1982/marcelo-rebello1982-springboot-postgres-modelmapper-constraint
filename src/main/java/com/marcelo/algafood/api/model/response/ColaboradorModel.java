package com.marcelo.algafood.api.model.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcelo.algafood.api.model.view.ColaboradorView;
import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ColaboradorModel {

    // retorna na consulta findById
    @JsonView(ColaboradorView.Resumo.class)
    private Long id;

    @JsonView(ColaboradorView.Resumo.class)
    private String nome;

    @JsonView(ColaboradorView.Resumo.class)
    private String cpfcnpj;

    @JsonView(ColaboradorView.Resumo.class)
    private String rg;

    @JsonView(ColaboradorView.Resumo.class)
    private EnderecoModel endereco;

    @JsonView(ColaboradorView.Resumo.class)
    private ColaboradorType colaboradorType;

    @JsonView(ColaboradorView.Resumo.class)
    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAtualizacao;

    private List<Cafe> cafeList = new ArrayList<>();

    @JsonView(ColaboradorView.Resumo.class)
    private List<Phone> phoneList = new ArrayList<>();

}
