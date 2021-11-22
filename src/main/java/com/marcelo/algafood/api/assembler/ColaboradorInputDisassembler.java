package com.marcelo.algafood.api.assembler;

import com.marcelo.algafood.api.model.input.ColaboradorInput;
import com.marcelo.algafood.domain.model.Colaborador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Colaborador toDomainObject(ColaboradorInput colaboradorToEntity) {
        return modelMapper.map(colaboradorToEntity, Colaborador.class);
    }


    public void copyToDomainObject(ColaboradorInput colaboradorToEntity, Colaborador colaborador) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        colaborador.setCafe(colaborador.getCafe());
        modelMapper.map(colaboradorToEntity, colaborador);
    }
}
