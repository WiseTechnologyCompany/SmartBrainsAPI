package br.com.wisefinances.smartbrains.model.dto;

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
public class CofreDTO {

    private Integer id;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    private TipoMovimentacaoDTO tipoMovimentacao;

    @NotNull
    private BigDecimal valor;

    private String observacao;

    private String dataAlteracao;

}