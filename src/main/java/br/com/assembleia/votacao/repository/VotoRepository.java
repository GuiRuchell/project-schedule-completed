package br.com.assembleia.votacao.repository;

import br.com.assembleia.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VotoRepository extends JpaRepository<Voto, Long> {

    Voto findByIduserAndIdpauta(Long iduser, Long idpauta);

    List<Voto> findByIdpauta(Long idpauta);
}
