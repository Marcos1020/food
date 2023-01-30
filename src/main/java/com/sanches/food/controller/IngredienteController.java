package com.sanches.food.controller;

import com.sanches.food.controller.request.IngredienteRequest;
import com.sanches.food.controller.response.IngredienteResponse;
import com.sanches.food.controller.response.UpdateIngredienteResponse;
import com.sanches.food.entity.IngredienteEntity;
import com.sanches.food.exception.ObjectAlreadyExists;
import com.sanches.food.exception.PreconditionFailedException;
import com.sanches.food.service.IngredienteService;
import com.sanches.food.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private IngredienteService service;
    @Autowired
    public IngredienteController(IngredienteService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<IngredienteResponse> cadastroDeComplemento(
            @RequestBody IngredienteRequest request) throws ObjectAlreadyExists {
        IngredienteResponse response = this.service.cadastrandoNovoIngrediente(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<IngredienteEntity> buscaIngredientes(){
        return service.listaDeIngredientes();
    }
    @GetMapping
    public List<IngredienteEntity> BuscaIngredientePorNome(
            @RequestParam(name = "nomeIngrediente", required = false, value = "nomeIngrediente") final String nomeIngrediente,
            @RequestParam(name = "idIngrediente", required = false, value = "idIngrediente")final Long idIngrediente){
        return this.service.buscaIngredientePorNome(nomeIngrediente, idIngrediente);
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateIngredienteResponse> AlteraUmIngrediente(
            @PathVariable("id")final Long idIngrediente,
            @RequestBody IngredienteRequest request) throws PreconditionFailedException{
        UpdateIngredienteResponse response = this.service.alteraUmIngredientePorId(idIngrediente, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>delataUmIngrediente(
            @PathVariable("id")final Long idIngrediente)throws PreconditionFailedException{
        this.service.deletaINgredientePorId(idIngrediente);
        return ResponseEntity.status(HttpStatus.OK).body(Constants.INGREDIENTE_DELETADO);
    }

}
