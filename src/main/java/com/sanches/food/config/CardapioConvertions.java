package com.sanches.food.config;

import com.sanches.food.controller.request.CardapioRequest;
import com.sanches.food.controller.response.CardapioResponse;
import com.sanches.food.controller.response.UpdateCardapioResponse;
import com.sanches.food.entity.CardapioEntity;
import com.sanches.food.entity.IngredienteEntity;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.repository.IngredienteRepository;
import com.sanches.food.utils.Constants;
import com.sanches.food.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CardapioConvertions {
    private IngredienteRepository ingredienteRepository;

    public CardapioConvertions(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
    }
    public CardapioEntity convertRequestToEntity(CardapioRequest request, CardapioEntity lanchesEntity) {
        lanchesEntity.setNome(request.getNome());
        lanchesEntity.setListaIngredientes(request.getListaIngredientes());
        lanchesEntity.setValor(request.getValor());
        lanchesEntity.setDataRegistro(ConverterUtil.nowTime());
        return lanchesEntity;
    }
    public CardapioResponse convertEntityToResponse(CardapioEntity entity){
        CardapioResponse response = new CardapioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setListaIngredientes(entity.getListaIngredientes());
        response.setValor(entity.getValor());
        response.setDataRegistro(ConverterUtil.nowTime());
        return response;
    }
    public void updateConvertRequestToEntity(CardapioRequest request, CardapioEntity entity){
        try {
            entity.setNome(request.getNome());
            entity.setValor(request.getValor());
            entity.setListaIngredientes(request.getListaIngredientes());
            entity.setDataAlteracao(ConverterUtil.nowTime());
        }catch (PreconditionFailedException exception){
            log.error(Constants.CAMPO_OBRIGATORIO);
            throw new PreconditionFailedException(Constants.CAMPO_OBRIGATORIO);
        }
    }
    public UpdateCardapioResponse convertUpadateentityToResponse(CardapioEntity entitySave){
        UpdateCardapioResponse response = new UpdateCardapioResponse();
        response.setIdLanche(entitySave.getId());
        response.setNomeDoLanche(entitySave.getNome());
        response.setListaIngredientes(entitySave.getListaIngredientes());
        response.setValor(entitySave.getValor());
        response.setDataAlteracao(ConverterUtil.nowTime());
        return response;
    }
    public void adicionaPromocaoHamburguer(CardapioRequest request, Double valorFinal) {
        Long quantidadeHamburguer = request.getComposicao().getQuantidadeCarne() - 1;
        IngredienteEntity entity = this.ingredienteRepository.findByNomeIngrediente(Constants.HAMBURGUER);
        double valor = entity.getValor() * quantidadeHamburguer;
        Double valorHamburguer = request.getValor() + valor + valorFinal;
        request.setValor(valorHamburguer);
    }
    public void adicionaPromocaoQUeijo(CardapioRequest request, Double valorFinal) {
        Long quantidadeQueijo = request.getComposicao().getQuantidadeQueijo() - 1;
        IngredienteEntity entity = this.ingredienteRepository.findByNomeIngrediente(Constants.QUEIJO);
        double valor = entity.getValor() * quantidadeQueijo;
        Double valorQueijo = request.getValor() + valor + valorFinal;
        request.setValor(valorQueijo);
    }

}
