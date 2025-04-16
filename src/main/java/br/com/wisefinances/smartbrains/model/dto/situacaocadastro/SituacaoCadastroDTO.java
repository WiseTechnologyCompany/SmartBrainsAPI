package br.com.wisefinances.smartbrains.model.dto.situacaocadastro;

import br.com.wisefinances.smartbrains.model.entity.situacaocadastro.SituacaoCadastro;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SituacaoCadastroDTO {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String descricao;

    public SituacaoCadastroDTO(SituacaoCadastro situacaoCadastro) {
        this.id = situacaoCadastro.getId();
        this.descricao = situacaoCadastro.getDescricao();
    }
}