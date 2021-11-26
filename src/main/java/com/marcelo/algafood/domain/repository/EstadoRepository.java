package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("select e from Estado e where e.nome = :nome")
    Estado isExists(@Param("nome") String nome);

}
