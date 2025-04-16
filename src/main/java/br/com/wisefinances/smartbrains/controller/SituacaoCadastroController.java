package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.situacaocadastro.SituacaoCadastroDTO;
import br.com.wisefinances.smartbrains.service.SituacaoCadastroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Situação Cadastro")
@RequestMapping("/v1/situacaocadastro")
@SecurityRequirement(name = "Authorization")
public class SituacaoCadastroController {

    @Autowired
    SituacaoCadastroService situacaoCadastroService;

    @GetMapping
    public ResponseEntity<List<SituacaoCadastroDTO>> getAll() {
        return ResponseEntity.ok(situacaoCadastroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoCadastroDTO> getSituacaoCadastroById(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveSituacaoCadastro(@RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.status(201).body(situacaoCadastroService.save(pSituacaoCadastroDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<SituacaoCadastroDTO> updateSituacaoCadastro(
            @PathVariable Integer id, @RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.ok(situacaoCadastroService.update(id, pSituacaoCadastroDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteSituacaoCadastro(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.delete(id));
    }
}