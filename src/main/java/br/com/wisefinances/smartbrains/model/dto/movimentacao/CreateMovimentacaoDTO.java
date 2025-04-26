package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.CreateMovimentacao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovimentacaoDTO {

    @NotNull
    private Integer usuario;

    @NotNull
    private Integer tipoMovimentacao;

    @NotNull
    private Integer tipoCategoria;

    @NotNull
    private String descricao;

    private String observacao;

    @NotNull
    private BigDecimal valor;

    private LocalDate dataCriacao;

    public CreateMovimentacaoDTO(CreateMovimentacao createMovimentacao) {
        this.usuario = createMovimentacao.getUsuario();
        this.tipoMovimentacao = createMovimentacao.getTipoMovimentacao();
        this.tipoCategoria = createMovimentacao.getTipoCategoria();
        this.descricao = createMovimentacao.getDescricao();
        this.observacao = createMovimentacao.getObservacao();
        this.valor = createMovimentacao.getValor();
        this.dataCriacao = createMovimentacao.getDataCriacao();
    }
}