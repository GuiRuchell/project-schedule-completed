package br.com.assembleia.votacao.shared.service;

import br.com.assembleia.votacao.model.Usuario;
import br.com.assembleia.votacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario getUser(Long id) {
        Optional response = repository.findById(id);
        if (response.isPresent()) {
            return repository.findById(id).get();
        }
        throw new RuntimeException("Usuário inexistente!");
    }

    @Transactional
    public Usuario insertUser(Usuario user) {
        if (user.getNome().isEmpty()) {
            throw new RuntimeException("Usuário inexistente!");
        }
        return repository.save(user);
    }
}
