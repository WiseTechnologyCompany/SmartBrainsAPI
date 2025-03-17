package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.GeneroDTO;
import br.com.wisefinances.smartbrains.service.GeneroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Gêneros")
@RequestMapping("/v1/generos")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getGeneroById(@PathVariable Integer id) {
        return ResponseEntity.ok(generoService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveGenero(@RequestBody @Valid GeneroDTO pGeneroDTO) {
        return ResponseEntity.status(201).body(generoService.save(pGeneroDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<GeneroDTO> updateGenero(@PathVariable Integer id, @RequestBody @Valid GeneroDTO pGeneroDTO) {
        return ResponseEntity.ok(generoService.update(id, pGeneroDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteGenero(@PathVariable Integer id) {
        return ResponseEntity.ok(generoService.delete(id));
    }
}