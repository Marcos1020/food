package com.sanches.food.repository;

import com.sanches.food.entity.IngredienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteEntity, Long> {

    IngredienteEntity findByNomeIngrediente(final String nomeIngrediente);
    IngredienteRepository findByNomeIngrediente(final Double valor);

    List<IngredienteEntity>findByNomeIngredienteOrIdIngrediente(@Param(value = "nomeIngrediente")final String nomeIngrediente,
                                                                @Param(value = "idIngrediente")final Long idIngrediente);



}
