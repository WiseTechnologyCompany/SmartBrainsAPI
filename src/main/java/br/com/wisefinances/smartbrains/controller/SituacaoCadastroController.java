package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.SituacaoCadastroDTO;
import br.com.wisefinances.smartbrains.service.SituacaoCadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/situacaocadastro")
public class SituacaoCadastroController {

    @Autowired
    SituacaoCadastroService situacaoCadastroService;

    @GetMapping
    public ResponseEntity<List<SituacaoCadastroDTO>> getAll() {
        return ResponseEntity.ok(situacaoCadastroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoCadastroDTO> findSituacaoCadastroById(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SituacaoCadastroDTO> saveSituacaoCadastro(@RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.status(201).body(situacaoCadastroService.save(pSituacaoCadastroDTO));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<SituacaoCadastroDTO> updateSituacaoCadastro(
            @PathVariable Integer id, @RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.ok(situacaoCadastroService.update(id, pSituacaoCadastroDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<MessagesResponseDTO> deleteSituacaoCadastro(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.delete(id));
    }
}