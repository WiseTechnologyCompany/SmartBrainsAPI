package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.CreateMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.MovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.UserTransactionsDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioInfoDTO;
import br.com.wisefinances.smartbrains.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Movimentação")
@RequestMapping("/v1/movimentacao")
@SecurityRequirement(name = "Authorization")
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 100, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(movimentacaoService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> getMovimentacaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoService.findById(id));
    }
    
    @PostMapping("/user")
    public ResponseEntity<List<UserTransactionsDTO>> getUserTransactions(@RequestBody @Valid UsuarioInfoDTO usuarioInfoDTO) {
        return ResponseEntity.ok(movimentacaoService.findAllUserTransactions(usuarioInfoDTO.getEmail()));
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