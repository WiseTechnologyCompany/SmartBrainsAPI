package br.com.wisefinances.smartbrains.infra.security.model.response.check;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckTokenDTO {

    @NotNull
    private String access_token;

}