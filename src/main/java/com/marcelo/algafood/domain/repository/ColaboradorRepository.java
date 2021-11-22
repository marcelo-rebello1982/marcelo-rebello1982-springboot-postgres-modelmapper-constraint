package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Colaborador;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends CustomJpaRepository<Colaborador, Long> {

}
