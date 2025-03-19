package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.create.dto.CreateMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.MovimentacaoDTO;
import br.com.wisefinances.smartbrains.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Movimentações")
@RequestMapping("/v1/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 100, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(movimentacaoService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> findMovimentacaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveMovimentacao(@RequestBody @Valid CreateMovimentacaoDTO createMovimentacaoDTO) {
        return ResponseEntity.status(201).body(movimentacaoService.save(createMovimentacaoDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<CreateMovimentacaoDTO> updateMovimentacao(@PathVariable Integer id, @RequestBody @Valid CreateMovimentacaoDTO createMovimentacaoDTO) {
        return ResponseEntity.ok(movimentacaoService.update(id, createMovimentacaoDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteMovimentacao(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoService.delete(id));
    }
}
