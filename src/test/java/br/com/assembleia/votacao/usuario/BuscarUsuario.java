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

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BuscarUsuario {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void sucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Guilherme");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        assertEquals(usuario, service.getUser(1L));

    }

    @Test
    public void erroNaoEncontrado() {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            service.getUser(1L);
        });
    }
}
