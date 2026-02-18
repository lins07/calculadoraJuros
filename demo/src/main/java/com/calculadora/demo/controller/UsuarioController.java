package com.calculadora.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários do sistema")
public class UsuarioController {

    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Obter todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados no sistema")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

}   




