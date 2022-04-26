package br.com.assembleia.votacao.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_votos")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idpauta;
    private Long iduser;
    private Boolean voto;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getIdpauta() {
        return idpauta;
    }

    public void setIdpauta(Long idpauta) {
        this.idpauta = idpauta;
    }

    public Long getIduser() {
        return iduser;

    }

    public void setIduser(Long iduser) {

        this.iduser = iduser;
    }

    public Boolean getVoto() {

        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }
}

