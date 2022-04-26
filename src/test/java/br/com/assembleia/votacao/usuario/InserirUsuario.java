package br.com.assembleia.votacao.usuario;

import br.com.assembleia.votacao.model.Usuario;
import br.com.assembleia.votacao.repository.UsuarioRepository;
import br.com.assembleia.votacao.shared.service.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class InserirUsuario {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void sucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Nome");
        Mockito.when(repository.save(usuario)).thenReturn(usuario);
        assertEquals(usuario, service.insertUser(usuario));
    }

    @Test
    public void erroNaoEncontrado() {
        Usuario usuario = new Usuario();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            service.insertUser(usuario);
        });
    }
}
