package com.calculadora.demo.service;

import org.springframework.stereotype.Service;

import com.calculadora.demo.model.JurosRequestDTO;
import com.calculadora.demo.model.JurosResponseDTO;

@Service
public class CalculadoraJurosService {

    public JurosResponseDTO calcular(JurosRequestDTO request) {

        double capital = request.capital();
        double taxa = request.taxa();
        int tempo = request.tempo();

        double montante;
        double juros;

        if ("simples".equalsIgnoreCase(request.tipo())) {
            juros = (capital * taxa * tempo) / 100.0;
            montante = capital + juros;
        } else {
            montante = capital * Math.pow(1 + taxa / 100.0, tempo);
            juros = montante - capital;
        }

        montante = round2(montante);
        juros = round2(juros);

        return new JurosResponseDTO(juros, montante);
    }

    private double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}