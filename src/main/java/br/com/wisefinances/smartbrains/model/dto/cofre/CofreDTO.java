package br.com.wisefinances.smartbrains.model.dto.cofre;

import br.com.wisefinances.smartbrains.model.dto.tipomovimentacao.TipoMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioDTO;
import br.com.wisefinances.smartbrains.model.entity.cofre.Cofre;
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
public class CofreDTO {

    private Integer id;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    private TipoMovimentacaoDTO tipoMovimentacao;

    @NotNull
    private BigDecimal valor;

    private String observacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    public CofreDTO(Cofre cofre) {
        this.id = cofre.getId();
        this.usuario = new UsuarioDTO(cofre.getUsuario());
        this.tipoMovimentacao = new TipoMovimentacaoDTO(cofre.getTipoMovimentacao());
        this.valor = cofre.getValor();
        this.observacao = cofre.getObservacao();
        this.dataCriacao = cofre.getDataCriacao();
    }
}