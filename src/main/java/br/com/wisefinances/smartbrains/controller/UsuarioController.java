package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.domain.page.PageDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.CreateUsuarioDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioDTO;
import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioInfoRequestDTO;
import br.com.wisefinances.smartbrains.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Usuários")
@RequestMapping("/v1/usuarios")
@SecurityRequirement(name = "Authorization")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 100, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(usuarioService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findUserById(id));
    }

    @PostMapping("/info")
    public ResponseEntity<UsuarioDTO> getUsuarioInfoByEmail(@RequestBody @Valid UsuarioInfoRequestDTO usuarioInfoRequestDTO) {
        return ResponseEntity.ok(usuarioService.findUsuarioInfoByEmail(usuarioInfoRequestDTO.getEmail()));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> saveUsuario(@RequestBody @Valid CreateUsuarioDTO createUsuarioDTO) {
        return ResponseEntity.status(201).body(usuarioService.save(createUsuarioDTO));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<CreateUsuarioDTO> updateUsuario(@PathVariable Integer id, @RequestBody @Valid CreateUsuarioDTO createUsuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, createUsuarioDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> deleteUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}