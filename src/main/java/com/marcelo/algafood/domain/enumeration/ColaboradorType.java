
package com.marcelo.algafood.domain.enumeration;

import com.marcelo.algafood.domain.model.CnpjGroup;
import com.marcelo.algafood.domain.model.CpfGroup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColaboradorType {

    FISICA("Fisica", "CPF", "000.000.000.00", CpfGroup.class),
    JURIDICA("Juridica", "CNPJ", "00.000.000/0000.00", CnpjGroup.class);

    private final String descricao;
    private final String documento;
    private final String mascara;
    private final Class<?> group;


}