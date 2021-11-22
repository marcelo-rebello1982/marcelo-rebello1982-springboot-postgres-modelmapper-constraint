package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Colaborador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends CustomJpaRepository<Colaborador, Long> {

    @Query("select c from Colaborador c where c.cpfcnpj = :cpfcnpj")
    Colaborador isExists(@Param("cpfcnpj") String cpfcnpj);

}
