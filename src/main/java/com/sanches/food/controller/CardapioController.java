package com.sanches.food.controller;

import com.sanches.food.controller.request.CardapioRequest;
import com.sanches.food.controller.response.CardapioResponse;
import com.sanches.food.controller.response.UpdateCardapioResponse;
import com.sanches.food.entity.CardapioEntity;
import com.sanches.food.exception.ObjectAlreadyExists;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.service.CardapioService;
import com.sanches.food.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    private CardapioService service;
    @Autowired
    public CardapioController(CardapioService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CardapioResponse>cadastrandoNovoLancheNoCardapio(
            @RequestBody CardapioRequest request,
            @RequestParam(value = "promocao", required = true)final String promocao){
        CardapioResponse response = this.service.cadastrarNovoItemNoCardapio(request, promocao);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/cadastrando/item-fixo")
    public ResponseEntity<CardapioResponse>cadastrandoNovoLancheFixoNocardapio(
            @RequestBody CardapioRequest request)throws ObjectAlreadyExists{
        CardapioResponse response = this.service.CadastraUmLancheFixoNoCardapio(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<CardapioEntity> buscaPorTodosOsLanchescadastradosnoSistema(){
        return service.buscaPorTodosItensDoCardapio();
    }
    @GetMapping
    public List<CardapioEntity> BuscaLanchePorNomeOuIdt(
            @RequestParam(name = "nomeDoLanche", required = false, value = "nomeDoLanche") final String nomeDoLanche,
            @RequestParam(name = "id", required = false, value = "id")final Long id) throws PreconditionFailedException {
        return this.service.buscaUmItemePorNomeOuId(nomeDoLanche, id);
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateCardapioResponse>alterandoUmLanche(
            @PathVariable("id")Long id,
            @RequestBody CardapioRequest request)throws PreconditionFailedException{
        UpdateCardapioResponse response= this.service.alteraItemDoCardapio(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>delataUmItemDoCArdapio(
            @PathVariable("id")final Long idLanche)throws PreconditionFailedException{
        this.service.deletaUmItemDoCardapioPorId(idLanche);
        return ResponseEntity.status(HttpStatus.OK).body(Constants.LANCHE__DELETADO);
    }
}
