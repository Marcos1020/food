package com.sanches.food.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.food.utils.Constants;
import com.sanches.food.utils.ConverterUtil;
import com.sanches.food.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CARDAPIO")
@SequenceGenerator(name = "sq_tb_lanches", sequenceName = Constants.SQ_TB_LANCHES, allocationSize = 1)
public class CardapioEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_lanches")
    @NotNull
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "VALOR")
    private Double valor;
    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTRO")
    private Date dataRegistro;
    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ALTERACAO")
    private Date dataAlteracao;
}
