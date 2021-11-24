package com.marcelo.algafood.api.assembler;

import com.marcelo.algafood.api.model.ColaboradorModel;
import com.marcelo.algafood.domain.model.Colaborador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColaboradorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ColaboradorModel toModel(Colaborador colaborador) {
        return modelMapper.map(colaborador, ColaboradorModel.class);
    }

    public List<ColaboradorModel> toCollectionModel(List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

