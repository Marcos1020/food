package com.sanches.food.controller.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardapioRequest {
    private String nome;
    private ComposicaoRequest composicao;
    private Double valor;
}
