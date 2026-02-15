package com.calculadora.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.calculadora.demo.model.Usuario;
import com.calculadora.demo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
    if (usuario.getName() == null || usuario.getEmail() == null || usuario.getPassword() == null) {
        throw new IllegalArgumentException("Todos os campos são obrigatórios");
    }
    return usuarioRepository.save(usuario);
    }

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }


}





   //public void deleteById(Long id) {
    //if (!usuarioRepository.existsById(id)) {
        //throw new IllegalArgumentException("Usuário não encontrado");
    //}
    //usuarioRepository.deleteById(id);
//}



