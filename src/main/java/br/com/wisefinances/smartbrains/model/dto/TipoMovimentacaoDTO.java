package br.com.wisefinances.smartbrains.model.dto;

import br.com.wisefinances.smartbrains.model.entity.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoMovimentacaoDTO {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String descricao;

    public TipoMovimentacaoDTO(TipoMovimentacao tipoMovimentacao) {
        this.id = tipoMovimentacao.getId();
        this.descricao = tipoMovimentacao.getDescricao();
    }
}