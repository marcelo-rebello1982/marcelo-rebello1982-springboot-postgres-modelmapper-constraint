package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("select e from Cidade e where e.nome = :nome")
    Cidade isExists(@Param("nome") String nome);

}
