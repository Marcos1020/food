package com.sanches.food.repository;

import com.sanches.food.entity.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioEntity, Long> {

    CardapioEntity findByNome(final String nome);
    List<CardapioEntity> findByNomeOrId (@Param(value = "nome")final String nome,
                                                      @Param(value = "id")final Long id);
}
