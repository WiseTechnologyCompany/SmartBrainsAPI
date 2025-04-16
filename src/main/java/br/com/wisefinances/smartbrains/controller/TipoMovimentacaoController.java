package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.tipomovimentacao.TipoMovimentacaoDTO;
import br.com.wisefinances.smartbrains.service.TipoMovimentacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Tipo Movimentação")
@RequestMapping("/v1/tipomovimentacao")
@SecurityRequirement(name = "Authorization")
public class TipoMovimentacaoController {

    @Autowired
    TipoMovimentacaoService tipoMovimentacaoService;

    @GetMapping
    public ResponseEntity<List<TipoMovimentacaoDTO>> getAll() {
        return ResponseEntity.ok(tipoMovimentacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMovimentacaoDTO> getTipoMovimentacaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoMovimentacaoService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveTipoMovimentacao(@RequestBody @Valid TipoMovimentacaoDTO pTipoMovimentacaoDTO) {
        return ResponseEntity.status(201).body(tipoMovimentacaoService.save(pTipoMovimentacaoDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<TipoMovimentacaoDTO> updateTipoMovimentacao(
            @PathVariable Integer id, @RequestBody @Valid TipoMovimentacaoDTO pTipoMovimentacaoDTO) {
        return ResponseEntity.ok(tipoMovimentacaoService.update(id, pTipoMovimentacaoDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteTipoMovimentacao(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoMovimentacaoService.delete(id));
    }
}