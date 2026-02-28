package com.calculadora.demo.model;

public record JurosRequestDTO(
    String tipo,
    Double capital,
    Double taxa,
    Integer tempo
) {}