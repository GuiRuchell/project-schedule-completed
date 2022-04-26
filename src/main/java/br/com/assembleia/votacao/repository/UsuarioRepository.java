package br.com.assembleia.votacao.repository;

import br.com.assembleia.votacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
