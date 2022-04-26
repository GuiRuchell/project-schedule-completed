package br.com.assembleia.votacao.votos;

import br.com.assembleia.votacao.dto.ResultadoVotacaoDto;
import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.model.Voto;
import br.com.assembleia.votacao.repository.PautaRepository;
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

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ResultadoVotacao {

    @InjectMocks
    private VotosService service;

    @Mock
    private VotoRepository repository;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void sucesso() {
        Voto voto = new Voto();
        Pauta pauta = new Pauta();
        ResultadoVotacaoDto dto = new ResultadoVotacaoDto(1L, 1, 0, "Pauta Aprovada");
        pauta.setId(1L);
        voto.setId(1L);
        voto.setVoto(true);
        pauta.setPrazo(new GregorianCalendar(2000, 01, 26).getTime());
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(voto));
        Mockito.when(repository.findByIdpauta(1L)).thenReturn(Arrays.asList(voto));
        ResultadoVotacaoDto res = service.resultadoVotacao(1L);
        assertEquals(Long.valueOf(1L), res.getIdPauta());
        assertEquals(Long.valueOf(1L), Long.valueOf((long) res.getSim()));
        assertEquals(Long.valueOf(0L), Long.valueOf((long) res.getNao()));
        assertEquals("Pauta Aprovada", res.getResultado());
    }

    @Test
    public void erroNaoEncontrado() {

        Voto voto = new Voto();
        Pauta pauta = new Pauta();
        ResultadoVotacaoDto dto = new ResultadoVotacaoDto(1L, 1, 0, "Pauta Aprovada");
        pauta.setId(1L);
        voto.setId(1L);
        voto.setVoto(true);
        pauta.setPrazo(new GregorianCalendar(2000, 01, 26).getTime());
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(voto));
        Mockito.when(repository.findByIdpauta(1L)).thenReturn(Arrays.asList(voto));
        Mockito.when(pautaRepository.findById(1L).get().getPrazo()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> {
            service.resultadoVotacao(1L);
        });
    }
}
