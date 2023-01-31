package com.sanches.food.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.food.utils.ConverterUtil;
import com.sanches.food.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardapioResponse {
    private Long id;
    private String nome;
    private Double valor;
    private String listaIngredientes;
    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    private Date dataRegistro;
}
