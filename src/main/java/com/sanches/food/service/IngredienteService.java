package com.sanches.food.service;

import com.sanches.food.config.IngredienteConvertions;
import com.sanches.food.controller.request.IngredienteRequest;
import com.sanches.food.controller.response.IngredienteResponse;
import com.sanches.food.controller.response.UpdateIngredienteResponse;
import com.sanches.food.entity.IngredienteEntity;
import com.sanches.food.exception.ObjectAlreadyExists;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.repository.IngredienteRepository;
import com.sanches.food.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class IngredienteService {

    private IngredienteRepository repository;
    private IngredienteConvertions convertions;

    @Autowired
    public IngredienteService(IngredienteRepository repository, IngredienteConvertions convertions) {
        this.repository = repository;
        this.convertions = convertions;
    }
    public IngredienteResponse cadastrandoNovoIngrediente(final IngredienteRequest request) throws ObjectAlreadyExists {
        log.info(Constants.CADASTRANDO_COMPLEMENTO);
        IngredienteEntity entity = this.repository.findByNomeIngrediente(request.getNomeIngrediente());
        if (Objects.nonNull(entity)) {
            log.error(Constants.COMPLEMENTO_JÁ_CADASTRADO);
            throw new ObjectAlreadyExists(Constants.COMPLEMENTO_JÁ_CADASTRADO);
        }
        IngredienteEntity ingredienteEntity = new IngredienteEntity();
        convertions.convertToRequestToEntity(request, ingredienteEntity);
        IngredienteEntity ingredienteSave = this.repository.save(ingredienteEntity);

        IngredienteResponse response = convertions.convertEntityToResponse(ingredienteSave);
        return response;
    }
    public List<IngredienteEntity> listaDeIngredientes()throws PreconditionFailedException{
        log.info(Constants.LISTA_DE_COMPLEMNTOS);
        return this.repository.findAll();
    }
    public List<IngredienteEntity> buscaIngredientePorNome(final String nomeIngrediente, final Long IdIngrediente){
        log.info(Constants.LISTA_DE_COMPLEMNTOS);
        return this.repository.findByNomeIngredienteOrIdIngrediente(nomeIngrediente, IdIngrediente);
    }
    public UpdateIngredienteResponse alteraUmIngredientePorId(final Long idIngrediente, final IngredienteRequest request)throws PreconditionFailedException{
        log.info(Constants.ALTERANDO_UM_COMPLEMTENTO);

        IngredienteEntity entity = this.repository.findById(idIngrediente).orElse(null);
        if (Objects.isNull(entity)){
            log.error(Constants.ID_NÃO_ENCONTRADO);
            throw new PreconditionFailedException(Constants.ID_NÃO_ENCONTRADO);
        }

        convertions.updateIngrediente(request, entity);
        IngredienteEntity entitySave = this.repository.save(entity);
        UpdateIngredienteResponse response = convertions.updateResponse(entitySave);
        return response;
    }
    public void deletaINgredientePorId(final Long idIngrediente) throws PreconditionFailedException{
        log.info(Constants.DELETANDO_INGREDIENTE);

        IngredienteEntity entity = this.repository.findById(idIngrediente).orElse(null);
        if(Objects.isNull(entity)){
            log.error(Constants.ID_NÃO_ENCONTRADO);
            throw new PreconditionFailedException(Constants.ID_NÃO_ENCONTRADO);
        }
        this.repository.delete(entity);
    }
}