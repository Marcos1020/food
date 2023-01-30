package com.sanches.food.config;

import com.sanches.food.controller.request.IngredienteRequest;
import com.sanches.food.controller.response.IngredienteResponse;
import com.sanches.food.controller.response.UpdateIngredienteResponse;
import com.sanches.food.entity.IngredienteEntity;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.utils.Constants;
import com.sanches.food.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class IngredienteConvertions {

    public IngredienteEntity convertToRequestToEntity(IngredienteRequest request, IngredienteEntity ingredienteEntity) {
        ingredienteEntity.setNomeIngrediente(request.getNomeIngrediente());
        ingredienteEntity.setValor(request.getValor());
        ingredienteEntity.setDataRegistro(ConverterUtil.nowTime());
        return ingredienteEntity;
    }
    public IngredienteResponse convertEntityToResponse(IngredienteEntity ingredienteSave) {
        IngredienteResponse response = new IngredienteResponse();
        response.setIdIngrediente(ingredienteSave.getIdIngrediente());
        response.setNomeIngrediente(ingredienteSave.getNomeIngrediente());
        response.setValor(ingredienteSave.getValor());
        response.setDataRegistro(ingredienteSave.getDataRegistro());
        return response;
    }
    public UpdateIngredienteResponse updateResponse(IngredienteEntity ingredienteSave) {
        UpdateIngredienteResponse response = new UpdateIngredienteResponse();
        response.setIdIngrediente(ingredienteSave.getIdIngrediente());
        response.setNomeIngrediente(ingredienteSave.getNomeIngrediente());
        response.setValor(ingredienteSave.getValor());
        response.setDataAlteracao(ingredienteSave.getDataAlteracao());
        return response;
    }

    public static void updateIngrediente(IngredienteRequest request, IngredienteEntity entity) {
        try{
            entity.setNomeIngrediente(request.getNomeIngrediente());
            entity.setValor(request.getValor());
            entity.setDataAlteracao(ConverterUtil.nowTime());}
        catch (PreconditionFailedException exception){
            log.error(Constants.CAMPO_OBRIGATORIO);
            throw new PreconditionFailedException(Constants.CAMPO_OBRIGATORIO);
        }
    }
}
