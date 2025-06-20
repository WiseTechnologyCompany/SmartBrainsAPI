package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.*;
import br.com.wisefinances.smartbrains.model.dto.usuario.UserEmailDTO;
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

    @GetMapping("/edit/{id}")
    public ResponseEntity<CreateMovimentacaoDTO> getTransactionById(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoService.findTransactionById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveMovimentacao(@RequestBody @Valid CreateMovimentacaoDTO createMovimentacaoDTO) {
        return ResponseEntity.status(201).body(movimentacaoService.save(createMovimentacaoDTO));
    }

    @PostMapping("/user/card")
    public ResponseEntity<TotalTransactionsResponseDTO> getUserTotalTransactions(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.sumUserTotalTransactions(userEmailDTO.getEmail()));
    }

    @PostMapping("/user/table")
    public ResponseEntity<List<UserTransactionsResponseDTO>> getAllUserTransactions(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.findAllUserTransactions(userEmailDTO.getEmail()));
    }

    @PostMapping("/total")
    public ResponseEntity<TotalEntradaSaidaPorAnoDTO> getTotalEntradaSaidaPorAno(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.getTotalEntradaSaidaPorAno(userEmailDTO.getEmail()));
    }

    @PostMapping("/total/entrada/mes")
    public ResponseEntity<List<TotalEntradaPorMesDTO>> getTotalEntradaPorMes(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.getTotalEntradaPorMes(userEmailDTO.getEmail()));
    }

    @PostMapping("/total/saida/mes")
    public ResponseEntity<List<TotalSaidaPorMesDTO>> getTotalSaidaPorMes(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.getTotalSaidaPorMes(userEmailDTO.getEmail()));
    }

    @PostMapping("/total/categoria")
    public ResponseEntity<List<TotalCategoriaDTO>> getTotalCategoria(@RequestBody @Valid UserEmailDTO userEmailDTO) {
        return ResponseEntity.ok(movimentacaoService.getTotalCategorias(userEmailDTO.getEmail()));
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