package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactionsDTO {

    @JsonProperty("id")
    private Integer transactionId;

    @JsonProperty("tipoMovimentacao")
    private String transactionType;

    @JsonProperty("descricao")
    private String transactionDescription;

    @JsonProperty("valor")
    private BigDecimal transactionValue;

    @JsonProperty("dataCriacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate transactionDate;

    public UserTransactionsDTO(Movimentacao movimentacao) {
        this.transactionId = movimentacao.getUsuario().getId();
        this.transactionType = movimentacao.getTipoMovimentacao().getDescricao();
        this.transactionDescription = movimentacao.getDescricao();
        this.transactionValue = movimentacao.getValor();
        this.transactionDate = movimentacao.getDataCriacao();
    }
}