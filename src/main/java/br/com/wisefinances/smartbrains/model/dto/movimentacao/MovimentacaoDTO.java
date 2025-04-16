package br.com.wisefinances.smartbrains.model.dto.movimentacao;

import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioDTO;
import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MovimentacaoDTO {

    private Integer id;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    private String tipoMovimentacao;

    @NotNull
    private BigDecimal valor;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    public MovimentacaoDTO(Movimentacao movimentacao) {
        this.id = movimentacao.getId();
        this.usuario = new UsuarioDTO(movimentacao.getUsuario());
        this.tipoMovimentacao = movimentacao.getTipoMovimentacao().getDescricao();
        this.valor = movimentacao.getValor();
        this.descricao = movimentacao.getDescricao();
        this.dataCriacao = movimentacao.getDataCriacao();
    }
}