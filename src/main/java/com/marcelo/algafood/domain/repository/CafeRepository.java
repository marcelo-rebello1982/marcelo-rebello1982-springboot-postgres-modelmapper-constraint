package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Cafe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends CustomJpaRepository<Cafe, Long> {

    @Query("select c from Cafe c where c.id = :cafeId")
    boolean findCafeById(@Param("cafeId") Long cafeId);

}
