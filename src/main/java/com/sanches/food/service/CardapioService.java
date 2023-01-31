package com.sanches.food.service;

import com.sanches.food.config.CardapioConvertions;
import com.sanches.food.controller.request.CardapioRequest;
import com.sanches.food.controller.response.CardapioResponse;
import com.sanches.food.controller.response.UpdateCardapioResponse;
import com.sanches.food.entity.CardapioEntity;
import com.sanches.food.entity.IngredienteEntity;
import com.sanches.food.exception.ObjectAlreadyExists;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.repository.CardapioRepository;
import com.sanches.food.repository.IngredienteRepository;
import com.sanches.food.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CardapioService {

    private CardapioRepository repository;
    private IngredienteRepository ingredienteRepository;
    private CardapioConvertions convertions;
    @Autowired
    public CardapioService(CardapioRepository repository, CardapioConvertions convertions, IngredienteRepository ingredienteRepository){
        this.repository = repository;
        this.convertions = convertions;
        this.ingredienteRepository = ingredienteRepository;
    }
    public CardapioResponse cadastrarNovoItemNoCardapio(final CardapioRequest request, final String promocao){
        log.info(Constants.CADASTRANDO_NOVA_PROMOCAO);
        if (!promocao.isEmpty()) {
            if (request.getComposicao().getSalada().equals(Constants.ALFACE) && request.getComposicao().getBaccon().equals(false)) {
                Double percentual =  10 / 100.0;
                Double valorFinal = request.getValor() - (percentual + request.getValor());

                if(request.getComposicao().getQuantidadeCarne() >= 3 ){
                    convertions.adicionaPromocaoHamburguer(request, valorFinal);
                }
                if(request.getComposicao().getQuantidadeQueijo() >= 3 ){
                    convertions.adicionaPromocaoQUeijo(request, valorFinal);
                }

            } else if (request.getComposicao().getQuantidadeCarne() >= 3) {
                convertions.adicionaPromocaoHamburguer(request, 0.0);

            } else {
                convertions.adicionaPromocaoQUeijo(request, 0.0);
            }
        }
            CardapioEntity lanchesEntity = new CardapioEntity();
            convertions.convertRequestToEntity(request, lanchesEntity);
            CardapioEntity entitySave = this.repository.save(lanchesEntity);

            CardapioResponse response = convertions.convertEntityToResponse(entitySave);
            return response;
        }
    public CardapioResponse CadastraUmLancheFixoNoCardapio(final CardapioRequest request)throws ObjectAlreadyExists{
        log.info(Constants.CADASTRANDO_NOVO_LANCHE);

        CardapioEntity entity = this.repository.findByNome(request.getNome());
        if (Objects.nonNull(entity)) {
            log.error(Constants.LANCHE_JA_CADASTRADO);
            throw new ObjectAlreadyExists(Constants.LANCHE_JA_CADASTRADO);
        }
        CardapioEntity lanchesEntity = new CardapioEntity();
        convertions.convertRequestToEntity(request, lanchesEntity);
        CardapioEntity entitySave = this.repository.save(lanchesEntity);

        CardapioResponse response = convertions.convertEntityToResponse(entitySave);
        return response;
    }

    public List<CardapioEntity> buscaPorTodosItensDoCardapio(){
        return this.repository.findAll();
    }
    public List<CardapioEntity> buscaUmItemePorNomeOuId(final String nome, final Long id){
        return this.repository.findByNomeOrId(nome, id);
    }
    public UpdateCardapioResponse alteraItemDoCardapio(final Long id, final CardapioRequest request)throws PreconditionFailedException{
        log.info(Constants.ALTERANDO_DADOS_DOS_LANCHES);

        CardapioEntity entity  = this.repository.findById(id).orElse(null);
        if(Objects.isNull(entity)){
            log.error(Constants.ID_NÃO_ENCONTRADO);
            throw new PreconditionFailedException(Constants.ID_NÃO_ENCONTRADO);

        }else if(entity.getNome() == request.getNome()) {
            log.error(Constants.UPDATE_EXCEPTION);
            throw new PreconditionFailedException((Constants.UPDATE_EXCEPTION));
        }
        convertions.updateConvertRequestToEntity(request, entity);
        CardapioEntity entitySave = this.repository.save(entity);

        UpdateCardapioResponse response = convertions.convertUpadateentityToResponse(entitySave);
        return response;
    }
    public void deletaUmItemDoCardapioPorId(final Long id) throws PreconditionFailedException{
        log.info(Constants.DELETANDO_LANCHE);

        CardapioEntity entity = this.repository.findById(id).orElse(null);
        if(Objects.isNull(entity)){
            log.error(Constants.ID_NÃO_ENCONTRADO);
            throw new PreconditionFailedException(Constants.ID_NÃO_ENCONTRADO);
        }
        this.repository.delete(entity);
    }
}
