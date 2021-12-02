package com.marcelo.algafood.domain.repository.spec;

import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.repository.filter.ColaboradorFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class ColaboradorSpec {

    public static Specification<Colaborador> usingFilter(ColaboradorFilter filter) {
        return (root, query, builder) -> {
            root.fetch("endereco").fetch("phone");
            root.fetch("colaborador");

            var predicates = new ArrayList<Predicate>();

            if (filter.getColaboradorId() != null) {
                predicates.add(builder.equal(root.get("colaborador"), filter.getColaboradorId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
