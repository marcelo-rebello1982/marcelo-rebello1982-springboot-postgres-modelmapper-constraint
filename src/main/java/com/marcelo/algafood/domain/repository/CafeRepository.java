package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Compromisso;
import com.marcelo.algafood.domain.model.Cozinha;
import com.marcelo.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends CustomJpaRepository<Cafe, Long> {

    @Query("select c from Cafe c where c.tipo = :tipo")
    Optional<Cafe> isExists(@Param("tipo") String tipo);

    @Query("select c from Cafe c where c.tipo = ?1")
    Optional<Cafe> findByTipo(String tipo);

//    @Query("select c from Cafe c where c.tipo = ?1")
//    List<Cafe> findByTipo(String tipo);


}
