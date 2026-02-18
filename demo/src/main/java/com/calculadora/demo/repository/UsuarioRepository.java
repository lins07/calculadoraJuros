package com.calculadora.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.calculadora.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);

    

}
