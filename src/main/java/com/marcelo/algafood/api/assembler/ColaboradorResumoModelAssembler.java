package com.marcelo.algafood.api.assembler;

import com.marcelo.algafood.api.model.response.ColaboradorModel;
import com.marcelo.algafood.api.model.response.ColaboradorResumoModel;
import com.marcelo.algafood.domain.model.Colaborador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColaboradorResumoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ColaboradorResumoModel toModel(Colaborador colaborador) {
        return modelMapper.map(colaborador, ColaboradorResumoModel.class);
    }

    public List<ColaboradorResumoModel> toCollectionModel(List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

