package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalTransactionsResponseDTO {

    private BigDecimal totalEntrada;

    private BigDecimal totalGastosFixos;

    private BigDecimal totalDespesas;

}