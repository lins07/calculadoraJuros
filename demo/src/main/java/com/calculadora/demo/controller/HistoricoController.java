package com.calculadora.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.calculadora.demo.model.Calculo;
import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.repository.CalculoRepository;

@RestController
@RequestMapping("/historico")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricoController {

    private final CalculoRepository calculoRepository;

    public HistoricoController(CalculoRepository calculoRepository) {
        this.calculoRepository = calculoRepository;
    }

    @GetMapping("/me")
    public List<Calculo> meusCalculos() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = (Usuario) auth.getPrincipal();

        return calculoRepository.findByUsuarioIdOrderByIdDesc(user.getId());
    }
}