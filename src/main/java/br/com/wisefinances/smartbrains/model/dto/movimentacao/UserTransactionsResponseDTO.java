package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactionsResponseDTO {

    @JsonProperty("id")
    private Integer transactionId;

    @JsonProperty("tipoMovimentacao")
    private String transactionType;

    @JsonProperty("tipoCategoria")
    private String transactionCategory;

    @JsonProperty("descricao")
    private String transactionDescription;

    @JsonProperty("valor")
    private String transactionValue;

    @JsonProperty("dataCriacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate transactionDate;

    public UserTransactionsResponseDTO(Movimentacao movimentacao) {
        this.transactionId = movimentacao.getId();
        this.transactionType = movimentacao.getTipoMovimentacao().getDescricao();
        this.transactionCategory = movimentacao.getTipoCategoria().getDescricao();
        this.transactionDescription = movimentacao.getDescricao();

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        this.transactionValue = numberFormat.format(movimentacao.getValor());

        this.transactionDate = movimentacao.getDataCriacao();
    }
}