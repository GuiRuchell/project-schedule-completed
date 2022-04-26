package br.com.assembleia.votacao.pauta;

import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.repository.PautaRepository;
import br.com.assembleia.votacao.shared.service.PautaService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class InserirSessao {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Test
    public void sucesso() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Descricao");
        pauta.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pauta));
        Mockito.when(repository.saveAndFlush(pauta)).thenReturn(pauta);
        assertEquals(pauta, service.insertSessao(pauta));
    }

    @Test
    public void erroNaoEncontrado() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setPrazo(new GregorianCalendar(2000, 01, 26).getTime());

        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pauta));
        assertThrows(RuntimeException.class, () -> {
            service.insertSessao(pauta);
        });
    }

}

