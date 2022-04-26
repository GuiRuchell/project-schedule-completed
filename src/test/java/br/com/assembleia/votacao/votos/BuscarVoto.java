package br.com.assembleia.votacao.votos;

import br.com.assembleia.votacao.model.Voto;
import br.com.assembleia.votacao.repository.VotoRepository;
import br.com.assembleia.votacao.shared.service.VotosService;
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
public class BuscarVoto {

    @InjectMocks
    private VotosService service;

    @Mock
    private VotoRepository repository;

    @Test
    public void sucesso() {
        Voto voto = new Voto();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(voto));
        assertEquals(voto, service.getVoto(1L));

    }

    @Test
    public void erroNaoEncontrado() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            service.getVoto(1L);
        });
    }
}