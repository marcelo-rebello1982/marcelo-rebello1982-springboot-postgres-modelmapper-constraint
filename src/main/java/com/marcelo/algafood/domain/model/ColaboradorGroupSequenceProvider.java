package com.marcelo.algafood.domain.model;

import com.marcelo.algafood.domain.model.Colaborador;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorGroupSequenceProvider implements DefaultGroupSequenceProvider<Colaborador> {

    public List<Class<?>> getValidationGroups(Colaborador colaborador) {

        List<Class<?>> groups = new ArrayList<>();
        groups.add(Colaborador.class);

        if (isSelectedPerson(colaborador)) {
            groups.add(colaborador.getColaboradorType().getGroup());
        }
        return groups;
    }
    public boolean isSelectedPerson(Colaborador colaborador) {
        return colaborador != null && colaborador.getColaboradorType() != null;
    }
}
