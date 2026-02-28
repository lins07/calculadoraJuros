package com.calculadora.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.calculadora.demo.config.TokenService;
import com.calculadora.demo.model.AuthenticationDTO;
import com.calculadora.demo.model.LoginResponseDTO;
import com.calculadora.demo.model.RegisterDTO;
import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UsuarioRepository repository,
            TokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetails) auth.getPrincipal();
        var token = tokenService.generateToken((Usuario) userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(token, data.email()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {

        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Usuario newUser = new Usuario(data.email(), encryptedPassword);
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}