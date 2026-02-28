package com.calculadora.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculadora.demo.model.Calculo;

public interface CalculoRepository extends JpaRepository<Calculo, Long> {

    List<Calculo> findByUsuarioIdOrderByIdDesc(Long usuarioId);
}