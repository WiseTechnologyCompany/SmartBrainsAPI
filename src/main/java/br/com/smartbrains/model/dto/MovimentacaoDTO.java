package br.com.smartbrains.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoDTO {

    private Integer id;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    private TipoMovimentacaoDTO tipoMovimentacao;

    @NotNull
    private BigDecimal valor;

    private String descricao;

    private Date dataCriacao;

}