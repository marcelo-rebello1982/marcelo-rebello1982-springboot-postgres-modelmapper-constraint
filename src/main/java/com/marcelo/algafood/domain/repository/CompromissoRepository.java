package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Compromisso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {

    @Query("select c from compromisso c where c.descricaoDoCompromisso = ?1")
    List<Compromisso> findBydescricao(String descricao);

//    @Query("select c from compromisso c where c.descricaoDoCompromisso = :name")
//    List<Compromisso> findBydescricaoDoCompromisso(@Param("name") String name, @Param("descricao") String descricao);


    //   @Query("select c from compromisso c where upper(c.descricaoDoCompromisso) = upper(?1)")
//    Optional<Compromisso> findBydescricaoDoCompromisso(String descricao);

    @Query("select c from compromisso c where c.dataDoCompromisso = ?1")
    Optional<Compromisso> findBydata(String data);


    @Query("select c from compromisso c where c.nomeDoResponsavel = ?1")
    Page<Compromisso> findAllByNome(Pageable pageable);

}
