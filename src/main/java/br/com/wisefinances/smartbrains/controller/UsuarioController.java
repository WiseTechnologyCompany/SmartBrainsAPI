package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.dto.UsuarioDTO;
import br.com.wisefinances.smartbrains.model.modify.dto.ModifyUsuarioDTO;
import br.com.wisefinances.smartbrains.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 50, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(usuarioService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessagesResponseDTO> saveUsuario(@RequestBody @Valid ModifyUsuarioDTO modifyUsuarioDTO) {
        return ResponseEntity.status(201).body(usuarioService.save(modifyUsuarioDTO));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ModifyUsuarioDTO> updateUsuario(@PathVariable Integer id, @RequestBody @Valid ModifyUsuarioDTO modifyUsuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, modifyUsuarioDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<MessagesResponseDTO> deleteUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}