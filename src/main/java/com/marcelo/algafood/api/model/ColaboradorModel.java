package com.marcelo.algafood.api.model;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Endereco;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ColaboradorModel {

    private Long id; // retorna na consulta findById
    private String nome;
    private String cpfcnpj;
    private EnderecoModel endereco;
    private ColaboradorType colaboradorType;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
    private List<Cafe> cafeList = new ArrayList<>();
    private List<Phone> phoneList = new ArrayList<>();

}
