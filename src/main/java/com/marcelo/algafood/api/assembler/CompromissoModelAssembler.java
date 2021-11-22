package com.marcelo.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompromissoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

//    public CompromissoModel toModel(Compromisso compromisso) {
//        return modelMapper.map(colaborador, ColaboradorModel.class);
//    }
//
//    public List<ColaboradorModel> toCollectionModel(List<Colaborador> colaboradores) {
//        return colaboradores.stream()
//                .map(this::toModel)
//                .collect(Collectors.toList());
//    }
}

