package br.com.wisefinances.smartbrains.model.create.dto;

import br.com.wisefinances.smartbrains.model.create.entity.CreateMovimentacao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovimentacaoDTO {

    private Integer id;

    @NotNull
    private Integer usuario;

    @NotNull
    private Integer tipoMovimentacao;

    @NotNull
    private BigDecimal valor;

    private String descricao;

    public CreateMovimentacaoDTO(CreateMovimentacao createMovimentacao) {
        this.id = createMovimentacao.getId();
        this.usuario = createMovimentacao.getUsuario();
        this.tipoMovimentacao = createMovimentacao.getTipomovimentacao();
        this.valor = createMovimentacao.getValor();
        this.descricao = createMovimentacao.getDescricao();
    }
}
