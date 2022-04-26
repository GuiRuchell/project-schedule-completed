package br.com.assembleia.votacao.votos;

import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.model.Voto;
import br.com.assembleia.votacao.repository.PautaRepository;
import br.com.assembleia.votacao.repository.UsuarioRepository;
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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class InserirVoto {

    @InjectMocks
    private VotosService service;

    @Mock
    private VotoRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void sucesso() {
        Voto voto = new Voto();
        Pauta pauta = new Pauta();

        Date date = new GregorianCalendar(2021, 01, 26).getTime();
        pauta.setPrazo(date);
        voto.setIdpauta(1L);
        voto.setIduser(1L);

        Mockito.when(pautaRepository.findById(voto.getIdpauta())).thenReturn(Optional.of(pauta));
        Mockito.when(pautaRepository.existsById(voto.getIduser())).thenReturn(true);
        Mockito.when(usuarioRepository.existsById(voto.getIdpauta())).thenReturn(true);
        Mockito.when(repository.findByIduserAndIdpauta(voto.getIduser(), voto.getIdpauta())).thenReturn(null);
        assertEquals(repository.save(voto), service.insertVoto(voto));
    }

    @Test
    public void erroVotoNaoEncontrado() {
        Voto voto = new Voto();
        Pauta pauta = new Pauta();
        voto.setIdpauta(1L);
        pauta.setId(1L);
        voto.setIduser(1L);

        Mockito.when(pautaRepository.findById(voto.getIdpauta())).thenReturn(Optional.of(pauta));
        Mockito.when(pautaRepository.existsById(voto.getIduser())).thenReturn(true);
        Mockito.when(usuarioRepository.existsById(voto.getIdpauta())).thenReturn(true);
        Mockito.when(repository.findByIduserAndIdpauta(voto.getIduser(), voto.getIdpauta())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> {
            service.insertVoto(voto);
        });
    }

    @Test
    public void erroNaoPodeVotarNovamenteEncontrado() {

        Voto voto = new Voto();
        Pauta pauta = new Pauta();
        voto.setIdpauta(1L);
        pauta.setId(1L);
        voto.setIduser(1L);

        Mockito.when(pautaRepository.findById(voto.getIdpauta())).thenReturn(Optional.of(pauta));
        Mockito.when(pautaRepository.existsById(voto.getIduser())).thenReturn(true);
        Mockito.when(usuarioRepository.existsById(voto.getIdpauta())).thenReturn(true);
        Mockito.when(repository.findByIduserAndIdpauta(voto.getIduser(), voto.getIdpauta())).thenReturn(voto);
        assertThrows(RuntimeException.class, () -> {
            service.insertVoto(voto);
        });
    }

    @Test
    public void erroPautaFechada() {

        Voto voto = new Voto();
        Pauta pauta = new Pauta();
        voto.setIdpauta(1L);
        pauta.setId(1L);
        voto.setIduser(1L);
        pauta.setPrazo(new GregorianCalendar(2021, 01, 26).getTime());

        Mockito.when(pautaRepository.findById(voto.getIdpauta())).thenReturn(Optional.of(pauta));
        Mockito.when(pautaRepository.existsById(voto.getIduser())).thenReturn(true);
        Mockito.when(usuarioRepository.existsById(voto.getIdpauta())).thenReturn(true);
        Mockito.when(repository.findByIduserAndIdpauta(voto.getIduser(), voto.getIdpauta())).thenReturn(voto);
        Mockito.when(pautaRepository.findById(1L).get().getPrazo()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> {
            service.insertVoto(voto);
        });

    }

}
