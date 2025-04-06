package br.com.wisefinances.smartbrains.infra.security.controller;

import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.AuthenticationResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.IsEmailValidResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.IsTokenValidResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.TokenResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.check.CheckEmailDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.check.CheckTokenDTO;
import br.com.wisefinances.smartbrains.infra.security.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/auth")
@Tag(name = "Autenticação")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Hidden
    @PostMapping("/check/token")
    public ResponseEntity<IsTokenValidResponseDTO> checkToken(@RequestBody @Valid CheckTokenDTO checkTokenDTO) {
        return ResponseEntity.status(200).body(autenticacaoService.checkToken(checkTokenDTO));
    }

    @Hidden
    @PostMapping("/check/email")
    public ResponseEntity<IsEmailValidResponseDTO> checkEmail(@RequestBody @Valid CheckEmailDTO checkEmailDTO) {
        return ResponseEntity.status(200).body(autenticacaoService.checkEmail(checkEmailDTO));
    }

    @Hidden
    @Transactional
    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponseDTO> saveAuth(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        return ResponseEntity.status(201).body(autenticacaoService.saveAuthentication(autenticacaoDTO));
    }

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<TokenResponseDTO> getToken(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        var authentication = authenticationManager.authenticate(autenticacaoDTO.convertAuthentication());
        return ResponseEntity.status(201).body(autenticacaoService.generateTokenForAuthentication(authentication));
    }
}