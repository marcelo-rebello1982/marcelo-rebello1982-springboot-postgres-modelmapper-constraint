package com.marcelo.algafood.api.assembler;

import com.marcelo.algafood.api.model.input.ColaboradorInput;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.model.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColaboradorInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Colaborador toDomainObject(ColaboradorInput colaboradorInput) {
        return modelMapper.map(colaboradorInput, Colaborador.class);
    }

    public void copyToDomainObject(ColaboradorInput colaboradorInput, Colaborador colaborador) {
        // para evitar identifier of an instance  Colaborador
        // was altered from 1 to 2 , NÃO RETIRAR LINHAS 28 para baixo
        List<Cafe> cafeList = new ArrayList<>();
        List<Phone> phoneList = new ArrayList<>();
        colaborador.setCafeList(cafeList);
        colaborador.setPhoneList(phoneList);
        if (colaborador.getEndereco() != null) {
            colaborador.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(colaboradorInput, colaborador);
    }
}
