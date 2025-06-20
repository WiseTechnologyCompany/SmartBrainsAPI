package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TotalEntradaPorMesDTO {

    private String mes;

    private BigDecimal totalEntrada;

}