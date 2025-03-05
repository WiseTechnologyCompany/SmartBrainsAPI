package br.com.smartbrains.controller;

import br.com.smartbrains.model.dto.EstadoCivilDTO;
import br.com.smartbrains.service.EstadoCivilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/estadocivil")
public class EstadoCivilController {

    @Autowired
    EstadoCivilService estadoCivilService;

    @GetMapping("/v1")
    public ResponseEntity<List<EstadoCivilDTO>> getAll() {
        return ResponseEntity.ok(estadoCivilService.findAll());
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<EstadoCivilDTO> getEstadoCivilById(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoCivilService.findById(id));
    }

    @PostMapping("/v1")
    @Transactional
    public ResponseEntity<EstadoCivilDTO> saveEstadoCivil(@RequestBody @Valid EstadoCivilDTO pEstadoCivilDTO) {
        return ResponseEntity.status(201).body(estadoCivilService.save(pEstadoCivilDTO));
    }

    @PatchMapping("/v1/{id}")
    @Transactional
    public ResponseEntity<EstadoCivilDTO> updateEstadoCivil(@PathVariable Integer id, @RequestBody @Valid EstadoCivilDTO pEstadoCivilDTO) {
        return ResponseEntity.ok(estadoCivilService.update(id, pEstadoCivilDTO));
    }

    @DeleteMapping("/v1/{id}")
    @Transactional
    public ResponseEntity<?> deleteGenero(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoCivilService.delete(id));
    }
}