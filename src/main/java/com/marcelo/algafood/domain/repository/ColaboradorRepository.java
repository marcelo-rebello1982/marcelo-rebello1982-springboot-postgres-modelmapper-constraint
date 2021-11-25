package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Colaborador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorRepository extends CustomJpaRepository<Colaborador, Long> {

    //https://prateek-ashtikar512.medium.com/dynamic-spring-projection-dbb4d360adf7

    @Query("select c from Colaborador c where c.cpfcnpj = :cpfcnpj")
    Colaborador isExists(@Param("cpfcnpj") String cpfcnpj);

    <T> List<T> findBy(String nome, Class<T> type);

    @Query("select c from Colaborador c where c.nome = :name or c.cpfcnpj = :type")
    <T> T findByNomeOrCpfcnpj(@Param("name") String name, Class<T> type);

    @Query("select c from Colaborador c where c.nome = ?1 and c.cpfcnpj = ?2")
    <T> T findByNomeAndCpfcnpj(String name, int age, Class<T> type);

}
