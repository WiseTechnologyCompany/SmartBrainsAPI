package br.com.wisefinances.smartbrains.infra.security.model.dto;

import br.com.wisefinances.smartbrains.infra.security.model.entity.Autenticacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDTO {

    private Integer id;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @NotBlank
    private String password;

    public AutenticacaoDTO(Autenticacao autenticacao) {
        this.id = autenticacao.getId();
        this.email = autenticacao.getUsername();
        this.password = autenticacao.getPassword();
    }

    public Autenticacao toEntity() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setId(this.id);
        autenticacao.setEmail(this.email);
        autenticacao.setPassword(this.password);
        return autenticacao;
    }

    public UsernamePasswordAuthenticationToken convertAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}