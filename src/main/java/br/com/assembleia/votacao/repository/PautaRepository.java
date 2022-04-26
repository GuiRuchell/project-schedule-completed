package br.com.assembleia.votacao.repository;

import br.com.assembleia.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}

