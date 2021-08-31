package com.restapi.usuarioapi.controller;

import com.restapi.usuarioapi.dto.MessageResponseDTO;
import com.restapi.usuarioapi.dto.UsuarioDTO;
import com.restapi.usuarioapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        return usuarioService.createUsuario(usuarioDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listAll() throws Exception {
        return usuarioService.listAll();
    }

    @GetMapping("/{cpf}")
    public List<UsuarioDTO> findByCpf(@PathVariable Long cpf) throws Exception {
        return usuarioService.findByCPF(cpf);
    }

    @PutMapping("/{cpf}")
    public MessageResponseDTO updateByCpf(@PathVariable Long cpf,
                                         @RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        return usuarioService.updateByCpf(cpf, usuarioDTO);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO deleteByCpf(@PathVariable Long cpf) throws Exception {
        return usuarioService.deleteByCpf(cpf);
    }
}
