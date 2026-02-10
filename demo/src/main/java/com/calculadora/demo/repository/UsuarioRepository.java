package com.calculadora.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    

}
