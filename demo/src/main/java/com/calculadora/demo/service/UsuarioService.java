package com.calculadora.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(long id) {
        usuarioRepository.deleteById(id);
    }

}
