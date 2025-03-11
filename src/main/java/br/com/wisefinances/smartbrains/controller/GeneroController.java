package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.model.dto.GeneroDTO;
import br.com.wisefinances.smartbrains.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
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

    @PostMapping
    @Transactional
    public ResponseEntity<GeneroDTO> saveGenero(@RequestBody @Valid GeneroDTO pGeneroDTO) {
        return ResponseEntity.status(201).body(generoService.save(pGeneroDTO));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<GeneroDTO> updateGenero(@PathVariable Integer id, @RequestBody @Valid GeneroDTO pGeneroDTO) {
        return ResponseEntity.ok(generoService.update(id, pGeneroDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteGenero(@PathVariable Integer id) {
        return ResponseEntity.ok(generoService.delete(id));
    }
}