package br.com.assembleia.votacao.shared.service;

import br.com.assembleia.votacao.dto.ResultadoVotacaoDto;
import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.model.Voto;
import br.com.assembleia.votacao.repository.PautaRepository;
import br.com.assembleia.votacao.repository.UsuarioRepository;
import br.com.assembleia.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VotosService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PautaRepository pautaRepository;

    public Voto getVoto(Long id) {
        Optional<Voto> response = votoRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw new RuntimeException("Votos não encontrada na base de dados!");
    }

    @Transactional
    public Voto insertVoto(Voto voto) {
        Date data = new Date();
        Optional<Pauta> pauta = pautaRepository.findById(voto.getIdpauta());
        if (pautaRepository.existsById(voto.getIdpauta()) &&
                userRepository.existsById(voto.getIduser())) {
            Voto votos = votoRepository.findByIduserAndIdpauta(voto.getIduser(), voto.getIdpauta());
            if (votos == null) {
                if (data.before(pauta.get().getPrazo())) {
                    return votoRepository.save(voto);
                }
                throw new RuntimeException("A pauta está fechada!");
            }
            throw new RuntimeException("Você não pode votar novamente!");
        }
        throw new RuntimeException("Pauta não encontrada na base de dados!");
    }

    public ResultadoVotacaoDto resultadoVotacao(Long idPauta) {
        Date data = new Date();
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        List<Voto> voto = votoRepository.findByIdpauta(idPauta);

        if (data.before(pauta.get().getPrazo())) {
            throw new RuntimeException("Votação em andamento!");
        }

        int sim = 0;
        int nao = 0;
        String resultado = "Resultado";

        for (int i = 0; i < voto.size(); i++) {

            if (voto.get(i).getVoto()) {
                sim++;
            } else {
                nao++;
            }
        }
        if (sim > nao) {
            resultado = "Pauta Aprovada";
        } else {
            resultado = "Pauta Reprovada";
        }

        ResultadoVotacaoDto resultadoVotacaoDto = new ResultadoVotacaoDto(idPauta, sim, nao, resultado);
        return resultadoVotacaoDto;
    }
}


