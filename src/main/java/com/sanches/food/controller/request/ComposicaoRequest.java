package com.sanches.food.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposicaoRequest {
    private String salada;
    private Long quantidadeCarne;
    private Long quantidadeQueijo;
    private Boolean baccon;
}
