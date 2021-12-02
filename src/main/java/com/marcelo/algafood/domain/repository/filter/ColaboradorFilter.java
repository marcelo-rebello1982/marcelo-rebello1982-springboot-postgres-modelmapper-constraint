package com.marcelo.algafood.domain.repository.filter;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.interfaces.CnpjGroup;
import com.marcelo.algafood.domain.model.interfaces.CpfGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ColaboradorFilter {

    private Long colaboradorId;
    private String rg;
    private String cpfcnpj;
    private String emailAddress;
    private ColaboradorType colaboradorType;

}
