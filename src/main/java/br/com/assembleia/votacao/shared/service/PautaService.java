package br.com.assembleia.votacao.shared.service;

import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    public Pauta getPauta(Long id) {
        Optional<Pauta> response = repository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }

    @Transactional
    public Pauta insertPauta(Pauta pauta) {
        return repository.save(pauta);
    }

    @Transactional
    public Pauta insertSessao(Pauta pauta) {
        Optional<Pauta> response = repository.findById(pauta.getId());
        Date data = new Date();

        if (response.isPresent()) {
            if (response.get().getPrazo() == null) {
                if (pauta.getPrazo() != null) {
                    response.get().setPrazo(pauta.getPrazo());
                    return repository.saveAndFlush(response.get());
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(data);
                    calendar.add(Calendar.MINUTE, 1);
                    calendar.getTime();
                    response.get().setPrazo(calendar.getTime());

                    return repository.saveAndFlush(response.get());
                }
            }
            throw new RuntimeException("A pauta está fechada!");
        }
        throw new RuntimeException("Pauta não encontrada na base de dados!");
    }
}












