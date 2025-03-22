package br.com.wisefinances.smartbrains.infra.security.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

    private String access_token;

    public TokenResponseDTO(String access_token) {
        this.access_token = access_token;
    }
}