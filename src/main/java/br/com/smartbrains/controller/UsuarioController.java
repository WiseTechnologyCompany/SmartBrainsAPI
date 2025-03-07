package br.com.smartbrains.controller;

import br.com.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.smartbrains.domain.page.PageDTO;
import br.com.smartbrains.model.dto.UsuarioDTO;
import br.com.smartbrains.model.dto.create.ModifyUsuarioDTO;
import br.com.smartbrains.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/v1")
    public ResponseEntity<PageDTO> getAll(@PageableDefault(size = 50, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(new PageDTO(usuarioService.findAll(pageable)));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<UsuarioDTO> findUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping("/v1")
    @Transactional
    public ResponseEntity<MessagesResponseDTO> saveUsuario(@RequestBody @Valid ModifyUsuarioDTO modifyUsuarioDTO) {
        return ResponseEntity.status(201).body(usuarioService.save(modifyUsuarioDTO));
    }
}