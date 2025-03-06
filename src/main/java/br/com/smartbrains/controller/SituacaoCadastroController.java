package br.com.smartbrains.controller;

import br.com.smartbrains.domain.messages.DeleteResponseDTO;
import br.com.smartbrains.model.dto.SituacaoCadastroDTO;
import br.com.smartbrains.service.SituacaoCadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/situacaocadastro")
public class SituacaoCadastroController {

    @Autowired
    SituacaoCadastroService situacaoCadastroService;

    @GetMapping("/v1")
    public ResponseEntity<List<SituacaoCadastroDTO>> getAll() {
        return ResponseEntity.ok(situacaoCadastroService.findAll());
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<SituacaoCadastroDTO> findSituacaoCadastroById(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.findById(id));
    }

    @PostMapping("/v1")
    @Transactional
    public ResponseEntity<SituacaoCadastroDTO> saveSituacaoCadastro(@RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.status(201).body(situacaoCadastroService.save(pSituacaoCadastroDTO));
    }

    @PatchMapping("/v1/{id}")
    @Transactional
    public ResponseEntity<SituacaoCadastroDTO> updateSituacaoCadastro(
            @PathVariable Integer id, @RequestBody @Valid SituacaoCadastroDTO pSituacaoCadastroDTO) {
        return ResponseEntity.ok(situacaoCadastroService.update(id, pSituacaoCadastroDTO));
    }

    @DeleteMapping("/v1/{id}")
    @Transactional
    public ResponseEntity<DeleteResponseDTO> deleteSituacaoCadastro(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoCadastroService.delete(id));
    }
}