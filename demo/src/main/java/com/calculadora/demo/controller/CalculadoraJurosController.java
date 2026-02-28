package com.calculadora.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.calculadora.demo.model.Calculo;
import com.calculadora.demo.model.JurosRequestDTO;
import com.calculadora.demo.model.JurosResponseDTO;
import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.repository.CalculoRepository;
import com.calculadora.demo.service.CalculadoraJurosService;

@RestController
@RequestMapping("/juros")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculadoraJurosController {

    private final CalculadoraJurosService service;
    private final CalculoRepository calculoRepository;

    public CalculadoraJurosController(CalculadoraJurosService service, CalculoRepository calculoRepository) {
        this.service = service;
        this.calculoRepository = calculoRepository;
    }

    @PostMapping("/calcular")
    public JurosResponseDTO calcular(@RequestBody JurosRequestDTO request) {

      
        JurosResponseDTO res = service.calcular(request);

       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof Usuario user) {

            Calculo calc = new Calculo();
            calc.setTipo(request.tipo());
            calc.setCapital(request.capital());
            calc.setTaxa(request.taxa());
            calc.setTempo(request.tempo());

            calc.setJuros(res.juros());
            calc.setMontante(res.montante());

            calc.setUsuario(user);

            calculoRepository.save(calc);
        }

        return res;
    }
}