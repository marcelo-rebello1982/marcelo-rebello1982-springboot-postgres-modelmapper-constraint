package com.marcelo.algafood.api.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
// @GroupSequenceProvider(ColaboradorGroupSequenceProvider.class)
public class ColaboradorInput extends AbstractEntity<Long> {

    private String nome;
    private ColaboradorType colaboradorType;
    private String cpfcnpj;
    private String rg;
    private Endereco endereco;
    private List<Cafe> cafeList = new ArrayList<>();
    private List<Phone> phoneList = new ArrayList<>();

}
