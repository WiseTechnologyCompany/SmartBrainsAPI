package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.create.dto.CreateCofreDTO;
import br.com.wisefinances.smartbrains.model.dto.CofreDTO;
import br.com.wisefinances.smartbrains.service.CofreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cofre")
@RequestMapping("/v1/cofre")
public class CofreController {

    @Autowired
    CofreService cofreService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 100, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(cofreService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CofreDTO> getCofreById(@PathVariable Integer id) {
        return ResponseEntity.ok(cofreService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveCofre(@RequestBody @Valid CreateCofreDTO createCofreDTO) {
        return ResponseEntity.status(201).body(cofreService.save(createCofreDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<CreateCofreDTO> updateCofre(@PathVariable Integer id, @RequestBody @Valid CreateCofreDTO createCofreDTO) {
        return ResponseEntity.ok(cofreService.update(id, createCofreDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteCofre(@PathVariable Integer id) {
        return ResponseEntity.ok(cofreService.delete(id));
    }
}