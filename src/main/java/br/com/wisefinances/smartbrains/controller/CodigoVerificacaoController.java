package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.model.dto.codigo.CodigoVerificacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.UserEmailDTO;
import br.com.wisefinances.smartbrains.service.codigo.CodigoVerificacaoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/v1/codigo")
@Tag(name = "Código de Verificação")
public class CodigoVerificacaoController {

    @Autowired
    CodigoVerificacaoService codigoVerificacaoService;

    @Transactional
    @PostMapping("/send")
    public void saveAndSendVerificationCode(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        codigoVerificacaoService.saveAndSendVerificationCode(userEmailDTO.getEmail());
    }

    @PostMapping("/verification")
    public ResponseEntity<Boolean> checkEmailAndVerificationCode(@RequestBody @NotNull CodigoVerificacaoDTO codigoVerificacaoDTO) {
        return ResponseEntity.ok(codigoVerificacaoService.checkEmailAndVerificationCode(codigoVerificacaoDTO));
    }
}